package com.mdwst.otterstream.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mdwst.otterstream.data.models.AddonCatalogCache
import com.mdwst.otterstream.data.models.AddonMetadataCache
import com.mdwst.otterstream.data.models.AddonStreamCache

@Dao
interface AddonCatalogDao {
    @Insert
    suspend fun insert(cache: AddonCatalogCache)

    @Query(
        """SELECT * FROM addon_catalogs 
        WHERE addonId = :addonId AND type = :type AND catalogId = :catalogId 
        AND (lastCached + ttl) > :currentTime"""
    )
    suspend fun getCatalog(
        addonId: String,
        type: String,
        catalogId: String,
        currentTime: Long = System.currentTimeMillis()
    ): AddonCatalogCache?

    @Query("DELETE FROM addon_catalogs WHERE (lastCached + ttl) < :currentTime")
    suspend fun clearExpired(currentTime: Long = System.currentTimeMillis())
}

@Dao
interface AddonMetadataDao {
    @Insert
    suspend fun insert(cache: AddonMetadataCache)

    @Query(
        """SELECT * FROM addon_metadata 
        WHERE metaId = :metaId AND addonId = :addonId 
        AND (lastCached + ttl) > :currentTime"""
    )
    suspend fun getMetadata(
        metaId: String,
        addonId: String,
        currentTime: Long = System.currentTimeMillis()
    ): AddonMetadataCache?

    @Query("DELETE FROM addon_metadata WHERE (lastCached + ttl) < :currentTime")
    suspend fun clearExpired(currentTime: Long = System.currentTimeMillis())
}

@Dao
interface AddonStreamDao {
    @Insert
    suspend fun insert(cache: AddonStreamCache)

    @Query(
        """SELECT * FROM addon_streams 
        WHERE streamId = :streamId AND addonId = :addonId 
        AND (lastCached + ttl) > :currentTime"""
    )
    suspend fun getStream(
        streamId: String,
        addonId: String,
        currentTime: Long = System.currentTimeMillis()
    ): AddonStreamCache?

    @Query("DELETE FROM addon_streams WHERE (lastCached + ttl) < :currentTime")
    suspend fun clearExpired(currentTime: Long = System.currentTimeMillis())
}
