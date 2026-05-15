package com.example.gramayatri.data.models

data class BusModel(

    val bookedSeats: Int = 0,

    val busNumber: String = "",

    val route: String = "",

    val eta: String = "",

    val occupancy: String = "",

    val speed: String = "",

    val totalSeats: Int = 0
) {

    /**
     * LIVE AVAILABLE SEATS
     */
    val availableSeats: Int

        get() = totalSeats - bookedSeats
}