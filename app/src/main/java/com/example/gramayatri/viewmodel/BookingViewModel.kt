package com.example.gramayatri.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gramayatri.data.models.BookingModel
import com.example.gramayatri.data.models.TripHistoryModel
import com.example.gramayatri.data.repository.BookingRepository
import com.example.gramayatri.data.repository.SeatRepository
import com.example.gramayatri.data.repository.TripHistoryRepository
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID

class BookingViewModel : ViewModel() {

    private val repository = BookingRepository()
    private val seatRepository = SeatRepository()
    private val historyRepository = TripHistoryRepository()
    private val auth = FirebaseAuth.getInstance()

    var isLoading by mutableStateOf(false)
        private set

    var bookingSuccess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    /**
     * INDUSTRIAL BOOKING LIFECYCLE
     */
    fun bookSeat(
        busNumber: String,
        route: String,
        passengerCount: Int,
        farePerPerson: Double = 45.0
    ) {
        val uid = auth.currentUser?.uid ?: run {
            errorMessage = "User not authenticated"
            return
        }

        isLoading = true
        errorMessage = ""

        // 1. Try to reduce seat first (Atomic Transaction)
        seatRepository.reduceSeat(busNumber) { committed ->
            if (committed) {
                // 2. Create Booking Entry
                val booking = BookingModel(
                    bookingId = UUID.randomUUID().toString(),
                    passengerId = uid,
                    busNumber = busNumber,
                    route = route,
                    passengerCount = passengerCount,
                    status = "CONFIRMED",
                    fare = farePerPerson * passengerCount
                )

                repository.saveBooking(
                    booking = booking,
                    onSuccess = {
                        // 3. Save to Trip History for offline access
                        val history = TripHistoryModel(
                            tripId = booking.bookingId,
                            busNumber = busNumber,
                            source = route.split(" → ").getOrNull(0) ?: "",
                            destination = route.split(" → ").getOrNull(1) ?: "",
                            fare = booking.fare,
                            status = "ACTIVE",
                            seats = passengerCount
                        )
                        historyRepository.addTripToHistory(uid, history)
                        
                        isLoading = false
                        bookingSuccess = true
                    },
                    onError = {
                        isLoading = false
                        errorMessage = it
                    }
                )
            } else {
                isLoading = false
                errorMessage = "Bus is full or booking failed"
            }
        }
    }
}
