package com.example.gramayatri.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramayatri.data.models.AdminAnalytics
import com.example.gramayatri.data.models.ConductorModel
import com.example.gramayatri.data.models.EmergencyAlert
import com.example.gramayatri.data.models.PassengerModel
import com.example.gramayatri.data.repository.AdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdminViewModel : ViewModel() {

    private val repository = AdminRepository()

    private val _analytics = MutableStateFlow(AdminAnalytics())
    val analytics: StateFlow<AdminAnalytics> = _analytics.asStateFlow()

    private val _emergencies = MutableStateFlow<List<EmergencyAlert>>(emptyList())
    val emergencies: StateFlow<List<EmergencyAlert>> = _emergencies.asStateFlow()

    private val _passengers = MutableStateFlow<List<PassengerModel>>(emptyList())
    val passengers: StateFlow<List<PassengerModel>> = _passengers.asStateFlow()

    private val _conductors = MutableStateFlow<List<ConductorModel>>(emptyList())
    val conductors: StateFlow<List<ConductorModel>> = _conductors.asStateFlow()

    init {
        listenToAll()
    }

    private fun listenToAll() {
        viewModelScope.launch {
            repository.listenToAnalytics().collect {
                _analytics.value = it
            }
        }
        viewModelScope.launch {
            repository.listenToEmergencies().collect {
                _emergencies.value = it
            }
        }
        viewModelScope.launch {
            repository.listenToPassengers().collect {
                _passengers.value = it
            }
        }
        viewModelScope.launch {
            repository.listenToConductors().collect {
                _conductors.value = it
            }
        }
    }

    fun resolveEmergency(alertId: String) {
        repository.resolveEmergency(alertId)
    }

    fun updatePassengerStatus(uid: String, status: String) {
        repository.updatePassengerStatus(uid, status)
    }

    fun updateConductorStatus(uid: String, status: String) {
        repository.updateConductorStatus(uid, status)
    }
}