package com.example.gramayatri.services

import com.example.gramayatri.data.models.BusLocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FleetTrackingManager {

    private val database = FirebaseDatabase.getInstance()
    private val liveBusesRef = database.getReference("live_buses")

    fun observeLiveBuses(): Flow<List<BusLocationModel>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buses = snapshot.children.mapNotNull { it.getValue(BusLocationModel::class.java) }
                trySend(buses)
            }
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        liveBusesRef.addValueEventListener(listener)
        awaitClose { liveBusesRef.removeEventListener(listener) }
    }
}