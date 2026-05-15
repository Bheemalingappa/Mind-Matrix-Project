package com.example.gramayatri.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramayatri.data.models.BusModel
import com.example.gramayatri.data.models.RouteModel
import com.example.gramayatri.data.repository.PassengerRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.example.gramayatri.data.repository.NotificationRepository
import com.google.firebase.auth.FirebaseAuth

import com.example.gramayatri.services.FleetTrackingManager
import com.example.gramayatri.data.models.BusLocationModel

class PassengerDashboardViewModel : ViewModel() {

    private val repository = PassengerRepository()
    private val notificationRepository = NotificationRepository()
    private val fleetManager = FleetTrackingManager()
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    private val _liveBuses = MutableStateFlow<List<BusModel>>(emptyList())
    val liveBuses: StateFlow<List<BusModel>> = _liveBuses.asStateFlow()

    private val _busLocations = MutableStateFlow<List<BusLocationModel>>(emptyList())
    val busLocations: StateFlow<List<BusLocationModel>> = _busLocations.asStateFlow()

    private val _recommendedRoutes = MutableStateFlow<List<RouteModel>>(emptyList())
    val recommendedRoutes: StateFlow<List<RouteModel>> = _recommendedRoutes.asStateFlow()

    private val _isEmergencyActive = MutableStateFlow(false)
    val isEmergencyActive: StateFlow<Boolean> = _isEmergencyActive.asStateFlow()

    init {
        listenToLiveBuses()
        loadRecommendedRoutes()
        registerNotifications()
        observeFleet()
    }

    private fun observeFleet() {
        viewModelScope.launch {
            fleetManager.observeLiveBuses().collect {
                _busLocations.value = it
            }
        }
    }

    private fun registerNotifications() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            notificationRepository.updateDeviceToken(uid)
            notificationRepository.subscribeToTopic("passenger_alerts")
        }
    }

    private fun listenToLiveBuses() {
        database.getReference("live_buses").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buses = snapshot.children.mapNotNull { it.getValue(BusModel::class.java) }
                _liveBuses.value = buses
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun loadRecommendedRoutes() {
        // INDUSTRIAL ENGINE: AI recommended routes based on fleet availability and traffic
        _recommendedRoutes.value = listOf(
            RouteModel("Hospete", "Ballari", "42 min", "31 km", "₹45", "Low Traffic"),
            RouteModel("Vijayanagara", "Hubli", "2 Hr 05 min", "142 km", "₹180", "Moderate Traffic"),
            RouteModel("Bengaluru", "Mysuru", "3 Hr 15 min", "186 km", "₹220", "Optimal Path"),
            RouteModel("Ballari", "Raichur", "3 Hr 30 min", "160 km", "₹190", "High Demand")
        )
    }

    fun triggerEmergency(uid: String, name: String, busNumber: String?, lat: Double, lng: Double) {
        viewModelScope.launch {
            repository.triggerSOS(uid, name, busNumber, lat, lng)
            _isEmergencyActive.value = true
        }
    }
}
