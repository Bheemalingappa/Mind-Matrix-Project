package com.example.gramayatri.data.repository

import com.example.gramayatri.data.local.TicketDao
import com.example.gramayatri.data.local.TicketEntity
import com.example.gramayatri.data.models.TicketModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TicketRepository(private val ticketDao: TicketDao? = null) {

    private val database =
        FirebaseDatabase.getInstance()

    fun saveTicket(
        ticket: TicketModel,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        database.reference
            .child("tickets")
            .child(ticket.ticketId)
            .setValue(ticket)
            .addOnSuccessListener {
                // Cache to local Room DB for offline access
                ticketDao?.let { dao ->
                    CoroutineScope(Dispatchers.IO).launch {
                        dao.insertTicket(
                            TicketEntity(
                                ticketId = ticket.ticketId,
                                busNumber = ticket.busNumber,
                                route = ticket.route,
                                passengerName = ticket.passengerName,
                                seatCount = ticket.seatCount,
                                fare = ticket.fare,
                                timestamp = ticket.timestamp
                            )
                        )
                    }
                }
                onSuccess()
            }
            .addOnFailureListener {
                onError(
                    it.message ?: "Ticket Failed"
                )
            }
    }

    fun getLocalTickets() = ticketDao?.getAllTickets()
}