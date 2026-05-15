package com.example.gramayatri.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramayatri.data.local.AppDatabase
import com.example.gramayatri.data.models.PassengerModel
import com.example.gramayatri.data.repository.PassengerRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val dao = AppDatabase.getDatabase(application).passengerDao()
    private val repository = PassengerRepository(dao)

    private val _profile = MutableStateFlow<PassengerModel?>(null)
    val profile: StateFlow<PassengerModel?> = _profile.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            repository.getPassengerProfile(uid).collect {
                _profile.value = it
            }
        }
    }

    fun updateProfile(profile: PassengerModel) {
        viewModelScope.launch {
            repository.updateProfile(profile)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}