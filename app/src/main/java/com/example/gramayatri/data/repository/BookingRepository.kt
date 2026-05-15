package com.example.gramayatri.data.repository

import com.example.gramayatri.data.models.BookingModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BookingRepository {

    private val database = FirebaseDatabase.getInstance()
    private val bookingRef = database.getReference("bookings")

    /**
     * SAVE BOOKING - Industrial Grade
     */
    fun saveBooking(
        booking: BookingModel,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        bookingRef.child(booking.bookingId).setValue(booking)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Booking Failed") }
    }

    /**
     * GET PASSENGER BOOKINGS
     */
    fun getPassengerBookings(uid: String, onData: (List<BookingModel>) -> Unit) {
        bookingRef.orderByChild("passengerId").equalTo(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val bookings = snapshot.children.mapNotNull { it.getValue(BookingModel::class.java) }
                    onData(bookings)
                }
                override fun onCancelled(error: DatabaseError) {}
            })
    }

    /**
     * CANCEL BOOKING
     */
    fun cancelBooking(bookingId: String, onSuccess: () -> Unit) {
        bookingRef.child(bookingId).removeValue().addOnSuccessListener { onSuccess() }
    }
}
