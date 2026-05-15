package com.example.gramayatri.data.models

data class WalletTransactionModel(
    val transactionId: String = "",
    val amount: Double = 0.0,
    val type: String = "DEBIT", // CREDIT, DEBIT
    val description: String = "",
    val timestamp: Long = System.currentTimeMillis()
)