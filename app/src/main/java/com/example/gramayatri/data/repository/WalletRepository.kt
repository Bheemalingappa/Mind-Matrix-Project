package com.example.gramayatri.data.repository

import com.example.gramayatri.data.local.PassengerDao
import com.example.gramayatri.data.local.WalletTransactionEntity
import com.example.gramayatri.data.models.WalletTransactionModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class WalletRepository(private val passengerDao: PassengerDao? = null) {

    private val database = FirebaseDatabase.getInstance()
    private val walletRef = database.getReference("wallets")
    private val userRef = database.getReference("users")

    fun getTransactions(uid: String): Flow<List<WalletTransactionModel>> {
        walletRef.child(uid).child("transactions").get().addOnSuccessListener { snapshot ->
            snapshot.children.forEach {
                val tx = it.getValue(WalletTransactionModel::class.java)
                tx?.let { t ->
                    CoroutineScope(Dispatchers.IO).launch {
                        passengerDao?.insertTransaction(
                            WalletTransactionEntity(
                                t.transactionId, t.amount, t.type, t.description, t.timestamp
                            )
                        )
                    }
                }
            }
        }

        return passengerDao?.getAllTransactions()?.map { list ->
            list.map {
                WalletTransactionModel(
                    it.transactionId, it.amount, it.type, it.description, it.timestamp
                )
            }
        } ?: kotlinx.coroutines.flow.flowOf(emptyList())
    }

    suspend fun addTransaction(uid: String, transaction: WalletTransactionModel) {
        val txId = walletRef.child(uid).child("transactions").push().key ?: return
        val tx = transaction.copy(transactionId = txId)
        walletRef.child(uid).child("transactions").child(txId).setValue(tx).await()

        // Update Balance
        val currentBalance = userRef.child(uid).child("walletBalance").get().await().getValue(Double::class.java) ?: 0.0
        val newBalance = if (tx.type == "CREDIT") currentBalance + tx.amount else currentBalance - tx.amount
        userRef.child(uid).child("walletBalance").setValue(newBalance).await()

        passengerDao?.insertTransaction(
            WalletTransactionEntity(
                tx.transactionId, tx.amount, tx.type, tx.description, tx.timestamp
            )
        )
    }
}