package com.example.gramayatri.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passenger_profile")
data class PassengerProfileEntity(
    @PrimaryKey val uid: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val membership: String,
    val walletBalance: Double,
    val profileImageUrl: String,
    val loyaltyPoints: Int,
    val totalDistanceTraveled: Double,
    val preferredLanguage: String
)
