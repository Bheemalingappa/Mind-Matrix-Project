package com.example.gramayatri.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramayatri.data.local.AppDatabase
import com.example.gramayatri.data.models.SavedRouteModel
import com.example.gramayatri.data.repository.PassengerRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRoutesViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val dao = AppDatabase.getDatabase(application).passengerDao()
    private val repository = PassengerRepository(dao)

    private val _savedRoutes = MutableStateFlow<List<SavedRouteModel>>(emptyList())
    val savedRoutes: StateFlow<List<SavedRouteModel>> = _savedRoutes.asStateFlow()

    init {
        loadSavedRoutes()
    }

    private fun loadSavedRoutes() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            repository.getSavedRoutes(uid).collect {
                _savedRoutes.value = it
            }
        }
    }

    fun saveRoute(source: String, destination: String) {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            val route = SavedRouteModel(
                routeId = System.currentTimeMillis().toString(),
                source = source,
                destination = destination
            )
            repository.saveRoute(uid, route)
        }
    }

    fun removeRoute(routeId: String) {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            repository.removeRoute(uid, routeId)
        }
    }
}