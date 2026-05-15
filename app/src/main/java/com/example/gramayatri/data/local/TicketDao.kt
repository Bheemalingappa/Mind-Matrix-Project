package com.example.gramayatri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TicketDao {
    @Query("SELECT * FROM tickets ORDER BY timestamp DESC")
    fun getAllTickets(): kotlinx.coroutines.flow.Flow<List<TicketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: TicketEntity)

    @Query("DELETE FROM tickets")
    suspend fun clearTickets()
}