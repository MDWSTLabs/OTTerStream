package com.mdwst.otterstream.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mdwst.otterstream.data.models.InstalledAddon
import kotlinx.coroutines.flow.Flow

@Dao
interface AddonDao {
    @Insert
    suspend fun insert(addon: InstalledAddon)

    @Update
    suspend fun update(addon: InstalledAddon)

    @Delete
    suspend fun delete(addon: InstalledAddon)

    @Query("SELECT * FROM addons WHERE id = :id")
    suspend fun getAddonById(id: String): InstalledAddon?

    @Query("SELECT * FROM addons ORDER BY name ASC")
    fun getAllAddons(): Flow<List<InstalledAddon>>

    @Query("SELECT * FROM addons WHERE isEnabled = 1 ORDER BY name ASC")
    fun getEnabledAddons(): Flow<List<InstalledAddon>>

    @Query("UPDATE addons SET isEnabled = :enabled WHERE id = :id")
    suspend fun setAddonEnabled(id: String, enabled: Boolean)

    @Query("DELETE FROM addons WHERE id = :id")
    suspend fun deleteAddonById(id: String)
}
