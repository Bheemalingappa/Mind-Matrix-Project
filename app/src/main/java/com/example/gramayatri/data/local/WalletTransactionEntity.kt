package com.example.gramayatri.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_transactions")
data class WalletTransactionEntity(
    @PrimaryKey val transactionId: String,
    val amount: Double,
    val type: String,
    val description: String,
    val timestamp: Long
)