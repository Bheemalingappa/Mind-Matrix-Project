package com.example.gramayatri.data.models

data class PassengerModel(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val status: String = "ACTIVE", // ACTIVE, BANNED
    val totalTickets: Int = 0,
    val lastActive: Long = System.currentTimeMillis(),
    val membership: String = "REGULAR", // REGULAR, PREMIUM, GOLD
    val walletBalance: Double = 0.0,
    val profileImageUrl: String = "",
    val loyaltyPoints: Int = 0,
    val totalDistanceTraveled: Double = 0.0,
    val preferredLanguage: String = "English"
)
