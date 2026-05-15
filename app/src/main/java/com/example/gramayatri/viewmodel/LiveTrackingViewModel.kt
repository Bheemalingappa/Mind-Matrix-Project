package com.example.gramayatri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gramayatri.data.models.BusLocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LiveTrackingViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance()

    private val _busLocation = MutableStateFlow<BusLocationModel?>(null)
    val busLocation: StateFlow<BusLocationModel?> = _busLocation.asStateFlow()

    fun trackBus(busNumber: String) {
        database.getReference("live_buses").child(busNumber.replace("-", "_"))
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val location = snapshot.getValue(BusLocationModel::class.java)
                    _busLocation.value = location
                }
                override fun onCancelled(error: DatabaseError) {}
            })
    }
}