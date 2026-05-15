package com.example.gramayatri.data

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
class FirebaseRepository {
    fun upvoteBusPing(
        pingId: String,
        currentUpvotes: Int
    ) {

        FirebaseDatabase.getInstance()
            .getReference("bus_pings")
            .child(pingId)
            .child("upvotes")
            .setValue(currentUpvotes + 1)
    }
    fun listenToBusPings(
        onDataLoaded: (List<BusPing>) -> Unit,
        onError: (String) -> Unit
    ) {

        val busPingsRef =
            FirebaseDatabase.getInstance().getReference("bus_pings")

        busPingsRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val busPings = mutableListOf<BusPing>()

                for (busSnapshot in snapshot.children) {

                    val busPing =
                        busSnapshot.getValue(BusPing::class.java)

                    if (busPing != null) {
                        busPings.add(busPing)
                    }
                }

                onDataLoaded(busPings)
            }

            override fun onCancelled(error: DatabaseError) {

                onError(error.message)
            }
        })
    }

    private val database =
        FirebaseDatabase.getInstance()

    private val busRef =
        database.getReference("bus_pings")

    fun sendBusPing(ping: BusPing) {

        val id = busRef.push().key ?: return

        busRef.child(id).setValue(ping)
    }
}
