package com.example.gramayatri.data.repository

import com.example.gramayatri.data.local.PassengerDao
import com.example.gramayatri.data.local.TripHistoryEntity
import com.example.gramayatri.data.models.TripHistoryModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TripHistoryRepository(private val passengerDao: PassengerDao? = null) {

    private val database = FirebaseDatabase.getInstance()
    private val historyRef = database.getReference("trip_history")

    fun getTripHistory(uid: String): Flow<List<TripHistoryModel>> {
        historyRef.child(uid).get().addOnSuccessListener { snapshot ->
            snapshot.children.forEach {
                val trip = it.getValue(TripHistoryModel::class.java)
                trip?.let { t ->
                    CoroutineScope(Dispatchers.IO).launch {
                        passengerDao?.insertTrip(
                            TripHistoryEntity(
                                t.tripId, t.busNumber, t.source, t.destination,
                                t.fare, t.timestamp, t.status, t.seats
                            )
                        )
                    }
                }
            }
        }

        return passengerDao?.getAllTrips()?.map { list ->
            list.map {
                TripHistoryModel(
                    it.tripId, it.busNumber, it.source, it.destination,
                    it.fare, it.timestamp, it.status, it.seats
                )
            }
        } ?: kotlinx.coroutines.flow.flowOf(emptyList())
    }

    fun addTripToHistory(uid: String, trip: TripHistoryModel) {
        historyRef.child(uid).child(trip.tripId).setValue(trip)
        // Also Cache to Room
        CoroutineScope(Dispatchers.IO).launch {
            passengerDao?.insertTrip(
                TripHistoryEntity(
                    trip.tripId, trip.busNumber, trip.source, trip.destination,
                    trip.fare, trip.timestamp, trip.status, trip.seats
                )
            )
        }
    }
}
