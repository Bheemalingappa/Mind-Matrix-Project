package com.example.gramayatri.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AlertRepository {

    private val alertsRef =
        FirebaseDatabase.getInstance().getReference("alerts")

    fun listenToAlerts(
        onDataLoaded: (List<Alert>) -> Unit,
        onError: (String) -> Unit
    ) {

        alertsRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val alerts = mutableListOf<Alert>()

                for (alertSnapshot in snapshot.children) {

                    val alert =
                        alertSnapshot.getValue(Alert::class.java)

                    if (alert != null) {
                        alerts.add(alert)
                    }
                }

                val sortedAlerts =
                    alerts.sortedByDescending { it.timestamp }

                onDataLoaded(sortedAlerts)
            }

            override fun onCancelled(error: DatabaseError) {

                onError(error.message)
            }
        })
    }
}