package com.example.gramayatri.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets")
data class TicketEntity(
    @PrimaryKey val ticketId: String,
    val busNumber: String,
    val route: String,
    val passengerName: String,
    val seatCount: Int,
    val fare: Int,
    val timestamp: Long
)