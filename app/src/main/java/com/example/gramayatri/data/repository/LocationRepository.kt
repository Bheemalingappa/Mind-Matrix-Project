package com.example.gramayatri.data.repository

import com.example.gramayatri.data.models.BusLocationModel
import com.google.firebase.database.*

class LocationRepository {

    private val database =
        FirebaseDatabase.getInstance()

    /**
     * UPDATE BUS LOCATION
     */
    fun updateBusLocation(

        location: BusLocationModel
    ) {

        database
            .getReference("live_bus_locations")
            .child(location.busNumber)
            .setValue(location)
    }

    /**
     * LISTEN LIVE LOCATIONS
     */
    fun listenBusLocations(

        onUpdate: (List<BusLocationModel>) -> Unit
    ) {

        database
            .getReference("live_bus_locations")

            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val buses =
                            mutableListOf<BusLocationModel>()

                        snapshot.children.forEach {

                            val bus =
                                it.getValue(
                                    BusLocationModel::class.java
                                )

                            if (bus != null) {

                                buses.add(bus)
                            }
                        }

                        onUpdate(buses)
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {
                    }
                }
            )
    }
}