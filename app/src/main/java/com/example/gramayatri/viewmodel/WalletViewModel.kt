package com.example.gramayatri.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramayatri.data.local.AppDatabase
import com.example.gramayatri.data.models.WalletTransactionModel
import com.example.gramayatri.data.repository.WalletRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WalletViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val dao = AppDatabase.getDatabase(application).passengerDao()
    private val repository = WalletRepository(dao)

    private val _transactions = MutableStateFlow<List<WalletTransactionModel>>(emptyList())
    val transactions: StateFlow<List<WalletTransactionModel>> = _transactions.asStateFlow()

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            repository.getTransactions(uid).collect {
                _transactions.value = it
            }
        }
    }

    fun addTransaction(amount: Double, type: String, description: String) {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            val tx = WalletTransactionModel(
                amount = amount,
                type = type,
                description = description
            )
            repository.addTransaction(uid, tx)
        }
    }
}