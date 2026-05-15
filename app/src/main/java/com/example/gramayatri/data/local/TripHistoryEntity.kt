package com.example.gramayatri.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_history")
data class TripHistoryEntity(
    @PrimaryKey val tripId: String,
    val busNumber: String,
    val source: String,
    val destination: String,
    val fare: Double,
    val timestamp: Long,
    val status: String,
    val seats: Int
)