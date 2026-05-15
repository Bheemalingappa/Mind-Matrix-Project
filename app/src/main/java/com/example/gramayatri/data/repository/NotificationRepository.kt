package com.example.gramayatri.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

class NotificationRepository {

    private val database = FirebaseDatabase.getInstance()
    private val tokensRef = database.getReference("device_tokens")

    suspend fun updateDeviceToken(uid: String) {
        val token = FirebaseMessaging.getInstance().token.await()
        tokensRef.child(uid).setValue(token).await()
    }

    fun subscribeToTopic(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
    }
}