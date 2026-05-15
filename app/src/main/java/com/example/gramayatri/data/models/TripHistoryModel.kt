package com.example.gramayatri.data.models

data class TripHistoryModel(
    val tripId: String = "",
    val busNumber: String = "",
    val source: String = "",
    val destination: String = "",
    val fare: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "COMPLETED", // COMPLETED, ACTIVE, CANCELLED
    val seats: Int = 1
)