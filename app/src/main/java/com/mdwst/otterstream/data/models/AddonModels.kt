package com.mdwst.otterstream.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// Database models for addon persistence
@Entity(tableName = "addons")
data class InstalledAddon(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String? = null,
    val manifestUrl: String,
    val version: String,
    val logo: String? = null,
    val isEnabled: Boolean = true,
    val installTime: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis(),
    val config: String? = null // JSON config for addon-specific settings
)

@Entity(tableName = "addon_catalogs")
data class AddonCatalogCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val addonId: String,
    val type: String, // "movie", "series", "tv"
    val catalogId: String,
    val data: String, // JSON serialized CatalogResponse
    val lastCached: Long = System.currentTimeMillis(),
    val ttl: Long = 3600000 // 1 hour default TTL
)

@Entity(tableName = "addon_metadata")
data class AddonMetadataCache(
    @PrimaryKey
    val metaId: String,
    val addonId: String,
    val type: String,
    val data: String, // JSON serialized MetaItem
    val lastCached: Long = System.currentTimeMillis(),
    val ttl: Long = 86400000 // 24 hour default TTL
)

@Entity(tableName = "addon_streams")
data class AddonStreamCache(
    @PrimaryKey
    val streamId: String,
    val addonId: String,
    val metaId: String,
    val type: String,
    val data: String, // JSON serialized StreamResponse
    val lastCached: Long = System.currentTimeMillis(),
    val ttl: Long = 3600000 // 1 hour default TTL
)

// Runtime models
data class AddonRequest(
    val addonId: String,
    val addonUrl: String,
    val endpoint: String,
    val path: String
)

data class AddonLoadResult(
    val addon: InstalledAddon,
    val manifest: AddonManifest,
    val isSuccess: Boolean,
    val error: String? = null
)

data class MergedCatalogItem(
    val meta: MetaItem,
    val source: ContentSource
)

enum class ContentSource {
    IPTV,
    ADDON,
    DEBRID,
    TMDB
}
