package com.example.gramayatri.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FleetMapViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance()

    private val _liveBuses = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val liveBuses: StateFlow<List<Map<String, Any>>> = _liveBuses.asStateFlow()

    init {
        listenToLiveBuses()
    }

    private fun listenToLiveBuses() {
        viewModelScope.launch {
            database.reference.child("live_buses").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val buses = snapshot.children.mapNotNull { it.value as? Map<String, Any> }
                    _liveBuses.value = buses
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}