package com.example.gramayatri.data.models

data class EmergencyAlert(
    val id: String = "",
    val busNumber: String = "",
    val conductorId: String = "",
    val type: String = "SOS", // SOS, Breakdown, Accident, Medical
    val message: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "ACTIVE" // ACTIVE, RESOLVED, ESCALATED
)