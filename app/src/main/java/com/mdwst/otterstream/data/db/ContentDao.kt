package com.mdwst.otterstream.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mdwst.otterstream.data.models.ContinueWatchingItem
import com.mdwst.otterstream.data.models.WatchlistItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistDao {
    @Insert
    suspend fun insert(item: WatchlistItem)

    @Update
    suspend fun update(item: WatchlistItem)

    @Delete
    suspend fun delete(item: WatchlistItem)

    @Query("SELECT * FROM watchlist ORDER BY addedAt DESC")
    fun getAllItems(): Flow<List<WatchlistItem>>

    @Query("SELECT * FROM watchlist WHERE id = :id")
    suspend fun getItemById(id: String): WatchlistItem?

    @Query("SELECT * FROM watchlist WHERE type = :type ORDER BY addedAt DESC")
    fun getItemsByType(type: String): Flow<List<WatchlistItem>>

    @Query("DELETE FROM watchlist WHERE id = :id")
    suspend fun deleteById(id: String)
}

@Dao
interface ContinueWatchingDao {
    @Insert
    suspend fun insert(item: ContinueWatchingItem)

    @Update
    suspend fun update(item: ContinueWatchingItem)

    @Delete
    suspend fun delete(item: ContinueWatchingItem)

    @Query("SELECT * FROM continue_watching ORDER BY lastWatched DESC LIMIT :limit")
    fun getRecentItems(limit: Int = 50): Flow<List<ContinueWatchingItem>>

    @Query("SELECT * FROM continue_watching WHERE id = :id")
    suspend fun getItemById(id: String): ContinueWatchingItem?

    @Query("DELETE FROM continue_watching WHERE id = :id")
    suspend fun deleteById(id: String)
}
