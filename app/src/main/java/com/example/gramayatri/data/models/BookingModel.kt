package com.example.gramayatri.data.models

data class BookingModel(
    val bookingId: String = "",
    val passengerId: String = "",
    val busNumber: String = "",
    val route: String = "",
    val passengerCount: Int = 1,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "PENDING", // PENDING, CONFIRMED, CANCELLED
    val fare: Double = 0.0
)
