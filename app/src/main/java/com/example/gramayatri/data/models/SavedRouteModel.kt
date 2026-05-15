package com.example.gramayatri.data.models

data class SavedRouteModel(
    val routeId: String = "",
    val source: String = "",
    val destination: String = "",
    val timestamp: Long = System.currentTimeMillis()
)