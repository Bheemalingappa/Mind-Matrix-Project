package com.example.gramayatri.utils

import com.example.gramayatri.data.RouteStop
import com.google.android.gms.maps.model.LatLng
import kotlin.math.*

object EtaCalculator {

    /**
     * DISTANCE CALCULATOR
     */
    private fun distanceInKm(

        start: LatLng,

        end: LatLng
    ): Double {

        val earthRadius = 6371.0

        val dLat =
            Math.toRadians(
                end.latitude - start.latitude
            )

        val dLng =
            Math.toRadians(
                end.longitude - start.longitude
            )

        val a =

            sin(dLat / 2).pow(2) +

            cos(Math.toRadians(start.latitude)) *

            cos(Math.toRadians(end.latitude)) *

            sin(dLng / 2).pow(2)

        val c =
            2 * atan2(
                sqrt(a),
                sqrt(1 - a)
            )

        return earthRadius * c
    }

    /**
     * MAP ETA CALCULATOR
     */
    fun calculateEta(

        current: LatLng,

        destination: LatLng,

        averageSpeedKmH: Double = 40.0
    ): Int {

        val distance =

            distanceInKm(
                current,
                destination
            )

        val timeHours =
            distance / averageSpeedKmH

        return (timeHours * 60)
            .roundToInt()
    }

    /**
     * ROUTE TIMELINE ETA
     */
    fun calculateEtas(

        currentStopIndex: Int,

        routeStops: List<RouteStop>
    ): Map<String, Int> {

        val etaMap =
            mutableMapOf<String, Int>()

        var etaMinutes = 0

        routeStops.forEachIndexed { index, stop ->

            if (index >= currentStopIndex) {

                etaMap[stop.stopName] =
                    etaMinutes

                etaMinutes += 5
            }
        }

        return etaMap
    }

    /**
     * FORMAT ETA
     */
    fun formatEta(
        minutes: Int
    ): String {

        return when {

            minutes <= 0 ->
                "Arriving"

            minutes < 60 ->
                "$minutes min"

            else -> {

                val hours =
                    minutes / 60

                val remainingMinutes =
                    minutes % 60

                "${hours}h ${remainingMinutes}m"
            }
        }
    }
}