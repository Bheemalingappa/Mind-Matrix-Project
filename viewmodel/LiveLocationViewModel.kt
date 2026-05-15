package com.example.gramayatri.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.gramayatri.data.models.BusLocationModel
import com.example.gramayatri.data.repository.LocationRepository

class LiveLocationViewModel : ViewModel() {

    private val repository =
        LocationRepository()

    var liveBusLocations by mutableStateOf<
            List<BusLocationModel>
            >(emptyList())

        private set

    init {

        repository.listenBusLocations {

            liveBusLocations = it
        }
    }
}