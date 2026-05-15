package com.example.gramayatri.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramayatri.data.local.AppDatabase
import com.example.gramayatri.data.models.TripHistoryModel
import com.example.gramayatri.data.repository.TripHistoryRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TripHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val dao = AppDatabase.getDatabase(application).passengerDao()
    private val repository = TripHistoryRepository(dao)

    private val _trips = MutableStateFlow<List<TripHistoryModel>>(emptyList())
    val trips: StateFlow<List<TripHistoryModel>> = _trips.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            repository.getTripHistory(uid).collect {
                _trips.value = it
            }
        }
    }
}