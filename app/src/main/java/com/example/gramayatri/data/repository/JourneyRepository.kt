package com.example.gramayatri.data.repository

import com.google.firebase.database.FirebaseDatabase

class JourneyRepository {

    private val database =
        FirebaseDatabase.getInstance()

    /**
     * START JOURNEY
     */
    fun startJourney(

        busNumber: String
    ) {

        database.reference

            .child("live_buses")

            .child(
                busNumber.replace("-", "_")
            )

            .child("journeyActive")

            .setValue(true)

        database.reference

            .child("live_buses")

            .child(
                busNumber.replace("-", "_")
            )

            .child("status")

            .setValue("LIVE")
    }

    /**
     * END JOURNEY
     */
    fun endJourney(

        busNumber: String
    ) {

        database.reference

            .child("live_buses")

            .child(
                busNumber.replace("-", "_")
            )

            .child("journeyActive")

            .setValue(false)

        database.reference

            .child("live_buses")

            .child(
                busNumber.replace("-", "_")
            )

            .child("status")

            .setValue("OFFLINE")
    }
}
