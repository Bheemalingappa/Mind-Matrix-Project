package com.example.gramayatri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PassengerDao {

    // Profile
    @Query("SELECT * FROM passenger_profile WHERE uid = :uid")
    fun getProfile(uid: String): Flow<PassengerProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: PassengerProfileEntity)

    // Trip History
    @Query("SELECT * FROM trip_history ORDER BY timestamp DESC")
    fun getAllTrips(): Flow<List<TripHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripHistoryEntity)

    // Wallet
    @Query("SELECT * FROM wallet_transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): Flow<List<WalletTransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: WalletTransactionEntity)

    // Saved Routes
    @Query("SELECT * FROM saved_routes ORDER BY timestamp DESC")
    fun getAllSavedRoutes(): Flow<List<SavedRouteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedRoute(route: SavedRouteEntity)

    @Query("DELETE FROM saved_routes WHERE routeId = :routeId")
    suspend fun deleteSavedRoute(routeId: String)
}