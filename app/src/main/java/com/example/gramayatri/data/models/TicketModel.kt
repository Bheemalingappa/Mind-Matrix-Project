package com.example.gramayatri.data.models

data class TicketModel(

    val ticketId: String = "",

    val busNumber: String = "",

    val route: String = "",

    val passengerName: String = "",

    val seatCount: Int = 1,

    val fare: Int = 0,

    val timestamp: Long =
        System.currentTimeMillis()
)