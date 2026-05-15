package com.example.gramayatri.data.models

data class AdminAnalytics(
    val activeBuses: Int = 0,
    val offlineBuses: Int = 0,
    val activeConductors: Int = 0,
    val totalPassengers: Int = 0,
    val dailyRevenue: Double = 0.0,
    val emergencyAlerts: Int = 0,
    val operationalRoutes: Int = 0,
    val journeyCompletionRate: Double = 0.0,
    val etaAccuracy: Double = 0.0
)