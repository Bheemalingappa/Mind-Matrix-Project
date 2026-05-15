package com.example.gramayatri.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_routes")
data class SavedRouteEntity(
    @PrimaryKey val routeId: String,
    val source: String,
    val destination: String,
    val timestamp: Long
)