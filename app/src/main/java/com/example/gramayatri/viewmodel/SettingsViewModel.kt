package com.example.gramayatri.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramayatri.data.repository.SettingsRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val repository = SettingsRepository()

    var notificationsEnabled by mutableStateOf(true)
    var darkModeEnabled by mutableStateOf(true)
    var selectedLanguage by mutableStateOf("English")

    init {
        loadSettings()
    }

    private fun loadSettings() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            val settings = repository.getSettings(uid)
            settings?.let {
                notificationsEnabled = it["notificationsEnabled"] as? Boolean ?: true
                darkModeEnabled = it["darkModeEnabled"] as? Boolean ?: true
                selectedLanguage = it["selectedLanguage"] as? String ?: "English"
            }
        }
    }

    fun saveSettings() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            val settings = mapOf(
                "notificationsEnabled" to notificationsEnabled,
                "darkModeEnabled" to darkModeEnabled,
                "selectedLanguage" to selectedLanguage
            )
            repository.saveSettings(uid, settings)
        }
    }
}