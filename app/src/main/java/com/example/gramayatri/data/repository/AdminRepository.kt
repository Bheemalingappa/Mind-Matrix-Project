package com.example.gramayatri.data.repository

import com.example.gramayatri.data.models.AdminAnalytics
import com.example.gramayatri.data.models.ConductorModel
import com.example.gramayatri.data.models.EmergencyAlert
import com.example.gramayatri.data.models.PassengerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AdminRepository {

    private val database = FirebaseDatabase.getInstance()

    /**
     * Listen to Realtime Fleet Analytics
     */
    fun listenToAnalytics(): Flow<AdminAnalytics> = callbackFlow {
        val ref = database.getReference("fleet_analytics")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val analytics = snapshot.getValue(AdminAnalytics::class.java) ?: AdminAnalytics()
                trySend(analytics)
            }
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    /**
     * Listen to Active Emergency Alerts
     */
    fun listenToEmergencies(): Flow<List<EmergencyAlert>> = callbackFlow {
        val ref = database.getReference("emergency_events")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val alerts = snapshot.children.mapNotNull { it.getValue(EmergencyAlert::class.java) }
                trySend(alerts)
            }
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    /**
     * Listen to Passengers
     */
    fun listenToPassengers(): Flow<List<PassengerModel>> = callbackFlow {
        val ref = database.getReference("users/passengers")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val passengers = snapshot.children.mapNotNull { it.getValue(PassengerModel::class.java) }
                trySend(passengers)
            }
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    /**
     * Listen to Conductors
     */
    fun listenToConductors(): Flow<List<ConductorModel>> = callbackFlow {
        val ref = database.getReference("users/conductors")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val conductors = snapshot.children.mapNotNull { it.getValue(ConductorModel::class.java) }
                trySend(conductors)
            }
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    /**
     * Resolve Emergency Alert
     */
    fun resolveEmergency(alertId: String) {
        database.getReference("emergency_events").child(alertId).child("status").setValue("RESOLVED")
    }

    /**
     * Update Passenger Status
     */
    fun updatePassengerStatus(uid: String, status: String) {
        database.getReference("users/passengers").child(uid).child("status").setValue(status)
    }

    /**
     * Update Conductor Status
     */
    fun updateConductorStatus(uid: String, status: String) {
        database.getReference("users/conductors").child(uid).child("status").setValue(status)
    }
}