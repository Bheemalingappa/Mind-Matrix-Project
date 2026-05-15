package com.example.gramayatri.data

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices

class LocationHelper(private val context: Context) {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        onLocationReceived: (Double, Double) -> Unit
    ) {

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->

                if (location != null) {

                    onLocationReceived(
                        location.latitude,
                        location.longitude
                    )
                }
            }
    }
}