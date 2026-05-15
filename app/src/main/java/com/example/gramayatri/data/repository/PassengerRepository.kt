package com.example.gramayatri.data.repository

import com.example.gramayatri.data.local.PassengerDao
import com.example.gramayatri.data.local.PassengerProfileEntity
import com.example.gramayatri.data.local.SavedRouteEntity
import com.example.gramayatri.data.models.PassengerModel
import com.example.gramayatri.data.models.SavedRouteModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PassengerRepository(private val passengerDao: PassengerDao? = null) {

    private val database = FirebaseDatabase.getInstance()
    private val passengersRef = database.getReference("users")
    private val savedRoutesRef = database.getReference("saved_routes")
    private val emergencyEventsRef = database.getReference("emergency_events")

    /**
     * Realtime Passenger Profile with Offline Cache
     */
    fun getPassengerProfile(uid: String): Flow<PassengerModel?> {
        // Sync from Firebase
        passengersRef.child(uid).get().addOnSuccessListener { snapshot ->
            val profile = snapshot.getValue(PassengerModel::class.java)
            profile?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    passengerDao?.insertProfile(
                        PassengerProfileEntity(
                            uid = it.uid,
                            name = it.name,
                            email = it.email,
                            phoneNumber = it.phoneNumber,
                            membership = it.membership,
                            walletBalance = it.walletBalance,
                            profileImageUrl = it.profileImageUrl,
                            loyaltyPoints = it.loyaltyPoints,
                            totalDistanceTraveled = it.totalDistanceTraveled,
                            preferredLanguage = it.preferredLanguage
                        )
                    )
                }
            }
        }

        // Return from local cache
        return passengerDao?.getProfile(uid)?.map { entity ->
            entity?.let {
                PassengerModel(
                    uid = it.uid,
                    name = it.name,
                    email = it.email,
                    phoneNumber = it.phoneNumber,
                    membership = it.membership,
                    walletBalance = it.walletBalance,
                    profileImageUrl = it.profileImageUrl,
                    loyaltyPoints = it.loyaltyPoints,
                    totalDistanceTraveled = it.totalDistanceTraveled,
                    preferredLanguage = it.preferredLanguage
                )
            }
        } ?: kotlinx.coroutines.flow.flowOf(null)
    }

    suspend fun updateProfile(profile: PassengerModel) {
        passengersRef.child(profile.uid).setValue(profile).await()
        passengerDao?.insertProfile(
            PassengerProfileEntity(
                uid = profile.uid,
                name = profile.name,
                email = profile.email,
                phoneNumber = profile.phoneNumber,
                membership = profile.membership,
                walletBalance = profile.walletBalance,
                profileImageUrl = profile.profileImageUrl,
                loyaltyPoints = profile.loyaltyPoints,
                totalDistanceTraveled = profile.totalDistanceTraveled,
                preferredLanguage = profile.preferredLanguage
            )
        )
    }

    /**
     * Trigger SOS Alert
     */
    fun triggerSOS(
        uid: String,
        passengerName: String,
        busNumber: String?,
        latitude: Double,
        longitude: Double,
        message: String = "Passenger triggered SOS"
    ) {
        val eventId = emergencyEventsRef.push().key ?: return
        val alert = mapOf(
            "id" to eventId,
            "passengerId" to uid,
            "passengerName" to passengerName,
            "busNumber" to busNumber,
            "type" to "PASSENGER_SOS",
            "message" to message,
            "latitude" to latitude,
            "longitude" to longitude,
            "timestamp" to System.currentTimeMillis(),
            "status" to "ACTIVE"
        )
        emergencyEventsRef.child(eventId).setValue(alert)
    }

    /**
     * Saved Routes with Cache
     */
    fun getSavedRoutes(uid: String): Flow<List<SavedRouteModel>> {
        savedRoutesRef.child(uid).get().addOnSuccessListener { snapshot ->
            snapshot.children.forEach {
                val route = it.getValue(SavedRouteModel::class.java)
                route?.let { r ->
                    CoroutineScope(Dispatchers.IO).launch {
                        passengerDao?.insertSavedRoute(
                            SavedRouteEntity(r.routeId, r.source, r.destination, r.timestamp)
                        )
                    }
                }
            }
        }

        return passengerDao?.getAllSavedRoutes()?.map { list ->
            list.map { SavedRouteModel(it.routeId, it.source, it.destination, it.timestamp) }
        } ?: kotlinx.coroutines.flow.flowOf(emptyList())
    }

    suspend fun saveRoute(uid: String, route: SavedRouteModel) {
        savedRoutesRef.child(uid).child(route.routeId).setValue(route).await()
        passengerDao?.insertSavedRoute(
            SavedRouteEntity(route.routeId, route.source, route.destination, route.timestamp)
        )
    }

    suspend fun removeRoute(uid: String, routeId: String) {
        savedRoutesRef.child(uid).child(routeId).removeValue().await()
        passengerDao?.deleteSavedRoute(routeId)
    }
}
