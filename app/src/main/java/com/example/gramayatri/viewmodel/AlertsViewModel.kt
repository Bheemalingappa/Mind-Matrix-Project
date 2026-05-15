package com.example.gramayatri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gramayatri.data.Alert
import com.example.gramayatri.data.AlertRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlertsViewModel : ViewModel() {

    private val repository = AlertRepository()

    private val _alerts = MutableStateFlow<List<Alert>>(emptyList())
    val alerts: StateFlow<List<Alert>> = _alerts.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchAlerts()
    }

    private fun fetchAlerts() {
        repository.listenToAlerts(
            onDataLoaded = {
                _alerts.value = it
                _isLoading.value = false
            },
            onError = {
                _isLoading.value = false
            }
        )
    }
}
