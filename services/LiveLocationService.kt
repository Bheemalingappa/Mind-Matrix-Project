package com.example.gramayatri.services

import android.annotation.SuppressLint
import android.content.Context
import com.example.gramayatri.data.models.BusLocationModel
import com.example.gramayatri.data.repository.LocationRepository
import com.google.android.gms.location.*

class LiveLocationService(

    private val context: Context
) {

    private val fusedLocationClient =

        LocationServices
            .getFusedLocationProviderClient(
                context
            )

    private val repository =
        LocationRepository()

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(

        busNumber: String
    ) {

        val request =

            LocationRequest.Builder(

                Priority.PRIORITY_HIGH_ACCURACY,

                5000
            ).build()

        fusedLocationClient
            .requestLocationUpdates(

                request,

                object : LocationCallback() {

                    override fun onLocationResult(
                        result: LocationResult
                    ) {

                        val location =
                            result.lastLocation
                                ?: return

                        repository.updateBusLocation(

                            BusLocationModel(

                                busNumber =
                                    busNumber,

                                latitude =
                                    location.latitude,

                                longitude =
                                    location.longitude
                            )
                        )
                    }
                },

                null
            )
    }
}