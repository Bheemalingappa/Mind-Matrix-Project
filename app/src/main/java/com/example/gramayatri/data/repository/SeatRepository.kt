package com.example.gramayatri.data.repository

import com.example.gramayatri.data.models.SeatState
import com.google.firebase.database.*

class SeatRepository {

    private val database = FirebaseDatabase.getInstance()
    private val seatRef = database.getReference("bus_seats")

    /**
     * REDUCE SEAT - Atomic Transaction
     */
    fun reduceSeat(busNumber: String, onComplete: (Boolean) -> Unit = {}) {
        val busSeatRef = seatRef.child(busNumber)
        
        busSeatRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result {
                val currentSeats = mutableData.getValue(Int::class.java) ?: 50
                if (currentSeats <= 0) {
                    return Transaction.abort()
                }
                mutableData.value = currentSeats - 1
                return Transaction.success(mutableData)
            }

            override fun onComplete(error: DatabaseError?, committed: Boolean, snapshot: DataSnapshot?) {
                onComplete(committed)
            }
        })
    }

    /**
     * REALTIME LISTENER
     */
    fun listenToSeats(onUpdate: (List<SeatState>) -> Unit) {
        seatRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val seats = snapshot.children.mapNotNull {
                    val busNumber = it.key ?: return@mapNotNull null
                    val availableSeats = it.getValue(Int::class.java) ?: 50
                    SeatState(busNumber, availableSeats)
                }
                onUpdate(seats)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
