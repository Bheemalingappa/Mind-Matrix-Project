package com.example.gramayatri.data

data class BusPing(
    val upvotes: Int = 0,
    val busId: String = "",
    val routeName: String = "",
    val stopName: String = "",
    val reporterName: String = "",
    val driverName: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timestamp: Long = 0,
    val etaMinutes: Int = 0
)
