package com.example.gramayatri.data.repository

import com.google.firebase.database.FirebaseDatabase

class SeatRepository {

    private val database =
        FirebaseDatabase.getInstance()

    fun reduceSeat(

        busNumber: String
    ) {

        val seatRef =

            database
                .getReference("bus_seats")
                .child(busNumber)

        seatRef.get()

            .addOnSuccessListener { snapshot ->

                val currentSeats =

                    snapshot
                        .getValue(Int::class.java)
                        ?: 50

                if (currentSeats > 0) {

                    seatRef.setValue(
                        currentSeats - 1
                    )
                }
            }
    }
}