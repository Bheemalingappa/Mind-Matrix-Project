package com.example.gramayatri.data.repository

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class SettingsRepository {

    private val database = FirebaseDatabase.getInstance()
    private val settingsRef = database.getReference("settings")

    suspend fun saveSettings(uid: String, settings: Map<String, Any>) {
        settingsRef.child(uid).updateChildren(settings).await()
    }

    suspend fun getSettings(uid: String): Map<String, Any>? {
        val snapshot = settingsRef.child(uid).get().await()
        return snapshot.value as? Map<String, Any>
    }
}