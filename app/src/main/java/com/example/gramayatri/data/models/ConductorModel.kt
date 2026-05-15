package com.example.gramayatri.data.models

data class ConductorModel(
    val uid: String = "",
    val name: String = "",
    val busNumber: String = "",
    val status: String = "OFFLINE", // ONLINE, OFFLINE, ON_JOURNEY
    val currentRoute: String = "",
    val phoneNumber: String = "",
    val attendanceCount: Int = 0,
    val performanceRating: Double = 5.0
)