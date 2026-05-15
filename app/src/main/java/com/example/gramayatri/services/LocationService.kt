package com.example.gramayatri.services

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.*
import com.google.firebase.database.FirebaseDatabase

class LocationService(
    private val context: Context
) {

    /**
     * FUSED LOCATION CLIENT
     */
    private val fusedLocationClient =

        LocationServices
            .getFusedLocationProviderClient(
                context
            )

    /**
     * FIREBASE
     */
    private val database =
        FirebaseDatabase.getInstance()

    /**
     * LOCATION REQUEST
     */
    private val locationRequest =

        LocationRequest.Builder(

            Priority.PRIORITY_HIGH_ACCURACY,

            5000
        )

            .setMinUpdateIntervalMillis(3000)

            .build()

    /**
     * START LIVE LOCATION UPDATES
     */
    @SuppressLint("MissingPermission")
    fun startLocationUpdates(

        conductorId: String,

        busNumber: String,

        route: String
    ) {

        fusedLocationClient
            .requestLocationUpdates(

                locationRequest,

                object : LocationCallback() {

                    override fun onLocationResult(
                        result: LocationResult
                    ) {

                        result.locations.forEach {

                            val busData = hashMapOf(

                                "busNumber" to busNumber,

                                "latitude" to it.latitude,

                                "longitude" to it.longitude,

                                "speed" to
                                    "${(it.speed * 3.6).toInt()} km/h",

                                "route" to route,

                                "journeyActive" to true,

                                "conductorId" to conductorId
                            )

                            database.reference

                                .child("live_buses")

                                .child(
                                    busNumber.replace("-", "_")
                                )

                                .setValue(busData)
                        }
                    }
                },

                Looper.getMainLooper()
            )
    }
}