package com.example.gramayatri.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gramayatri.data.models.BusModel
import com.example.gramayatri.data.models.SeatState
import com.example.gramayatri.data.repository.SeatRepository

class PassengerViewModel : ViewModel() {


    private val seatRepository =
    SeatRepository()

    var liveSeats by mutableStateOf<List<SeatState>>(
    emptyList()
)
    private set

    init {

    seatRepository.listenToSeats {

        liveSeats = it
    }
}

    /**
     * SEARCH QUERY
     */
    var searchQuery by mutableStateOf("")
        private set

    /**
     * UPDATE SEARCH
     */
    fun updateSearch(
        query: String
    ) {

        searchQuery = query
    }

    /**
     * FILTER BUSES
     */
    fun filterBuses(
        buses: List<BusModel>
    ): List<BusModel> {

        if (searchQuery.isBlank()) {

            return buses
        }

        return buses.filter {

            it.busNumber.contains(
                searchQuery,
                ignoreCase = true
            ) ||

            it.route.contains(
                searchQuery,
                ignoreCase = true
            )
        }
    }
}