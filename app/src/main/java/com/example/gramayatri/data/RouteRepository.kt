package com.example.gramayatri.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RouteRepository {

    private val routesRef =
        FirebaseDatabase.getInstance().getReference("routes")

    fun listenToRouteStops(
        routeId: String,
        onDataLoaded: (List<RouteStop>) -> Unit,
        onError: (String) -> Unit
    ) {

        routesRef
            .child(routeId)
            .child("stops")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val routeStops = mutableListOf<RouteStop>()

                    for (stopSnapshot in snapshot.children) {

                        val stop =
                            stopSnapshot.getValue(RouteStop::class.java)

                        if (stop != null) {
                            routeStops.add(stop)
                        }
                    }

                    val sortedStops =
                        routeStops.sortedBy { it.order }

                    onDataLoaded(sortedStops)
                }

                override fun onCancelled(error: DatabaseError) {
                    onError(error.message)
                }
            })
    }
}