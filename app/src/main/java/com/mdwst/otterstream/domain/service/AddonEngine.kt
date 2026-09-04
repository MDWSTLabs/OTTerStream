package com.mdwst.otterstream.domain.service

import com.mdwst.otterstream.data.api.StremioAddonApi
import com.mdwst.otterstream.data.db.AddonCatalogDao
import com.mdwst.otterstream.data.db.AddonDao
import com.mdwst.otterstream.data.db.AddonMetadataDao
import com.mdwst.otterstream.data.db.AddonStreamDao
import com.mdwst.otterstream.data.models.AddonCatalogCache
import com.mdwst.otterstream.data.models.AddonLoadResult
import com.mdwst.otterstream.data.models.AddonManifest
import com.mdwst.otterstream.data.models.AddonMetadataCache
import com.mdwst.otterstream.data.models.AddonStreamCache
import com.mdwst.otterstream.data.models.CatalogResponse
import com.mdwst.otterstream.data.models.InstalledAddon
import com.mdwst.otterstream.data.models.MetaItem
import com.mdwst.otterstream.data.models.StreamResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

class AddonEngine @Inject constructor(
    private val addonApi: StremioAddonApi,
    private val addonDao: AddonDao,
    private val catalogDao: AddonCatalogDao,
    private val metadataDao: AddonMetadataDao,
    private val streamDao: AddonStreamDao,
    private val json: Json
) {

    fun getAllAddons(): Flow<List<InstalledAddon>> = addonDao.getAllAddons()

    fun getEnabledAddons(): Flow<List<InstalledAddon>> = addonDao.getEnabledAddons()

    suspend fun installAddon(manifestUrl: String): AddonLoadResult {
        return try {
            val manifest = addonApi.getManifest(manifestUrl)
            val addon = InstalledAddon(
                id = manifest.id,
                name = manifest.name,
                description = manifest.description,
                manifestUrl = manifestUrl,
                version = manifest.version,
                logo = manifest.logo,
                isEnabled = true
            )
            addonDao.insert(addon)
            AddonLoadResult(addon, manifest, isSuccess = true)
        } catch (e: Exception) {
            Timber.e(e, "Failed to install addon from $manifestUrl")
            AddonLoadResult(
                addon = InstalledAddon(
                    id = "error",
                    name = "Error",
                    manifestUrl = manifestUrl,
                    version = "0"
                ),
                manifest = AddonManifest(
                    id = "error",
                    version = "0",
                    name = "Error",
                    resources = emptyList(),
                    types = emptyList(),
                    catalogs = emptyList()
                ),
                isSuccess = false,
                error = e.message
            )
        }
    }

    suspend fun getCatalog(
        addonId: String,
        type: String,
        catalogId: String
    ): Result<CatalogResponse> {
        return try {
            // Check cache first
            val cached = catalogDao.getCatalog(addonId, type, catalogId)
            if (cached != null) {
                return Result.success(json.decodeFromString(cached.data))
            }

            val addon = addonDao.getAddonById(addonId) ?: return Result.failure(
                Exception("Addon not found")
            )

            val path = "catalog/$type/$catalogId.json"
            val response = addonApi.getCatalog(addon.manifestUrl.substringBeforeLast("/"), path)

            // Cache the result
            catalogDao.insert(
                AddonCatalogCache(
                    addonId = addonId,
                    type = type,
                    catalogId = catalogId,
                    data = json.encodeToString(response)
                )
            )

            Result.success(response)
        } catch (e: Exception) {
            Timber.e(e, "Failed to get catalog")
            Result.failure(e)
        }
    }

    suspend fun getMeta(
        addonId: String,
        type: String,
        id: String
    ): Result<MetaItem> {
        return try {
            val metaId = "$type:$id"
            
            // Check cache
            val cached = metadataDao.getMetadata(metaId, addonId)
            if (cached != null) {
                return Result.success(json.decodeFromString(cached.data))
            }

            val addon = addonDao.getAddonById(addonId) ?: return Result.failure(
                Exception("Addon not found")
            )

            val path = "meta/$type/$id.json"
            val response = addonApi.getMeta(addon.manifestUrl.substringBeforeLast("/"), path)

            // Cache the result
            metadataDao.insert(
                AddonMetadataCache(
                    metaId = metaId,
                    addonId = addonId,
                    type = type,
                    data = json.encodeToString(response.meta)
                )
            )

            Result.success(response.meta)
        } catch (e: Exception) {
            Timber.e(e, "Failed to get metadata")
            Result.failure(e)
        }
    }

    suspend fun getStreams(
        addonId: String,
        type: String,
        id: String
    ): Result<StreamResponse> {
        return try {
            val streamId = "$type:$id"
            
            // Check cache
            val cached = streamDao.getStream(streamId, addonId)
            if (cached != null) {
                return Result.success(json.decodeFromString(cached.data))
            }

            val addon = addonDao.getAddonById(addonId) ?: return Result.failure(
                Exception("Addon not found")
            )

            val path = "stream/$type/$id.json"
            val response = addonApi.getStreams(addon.manifestUrl.substringBeforeLast("/"), path)

            // Cache the result
            streamDao.insert(
                AddonStreamCache(
                    streamId = streamId,
                    addonId = addonId,
                    metaId = id,
                    type = type,
                    data = json.encodeToString(response)
                )
            )

            Result.success(response)
        } catch (e: Exception) {
            Timber.e(e, "Failed to get streams")
            Result.failure(e)
        }
    }

    suspend fun setAddonEnabled(addonId: String, enabled: Boolean) {
        addonDao.setAddonEnabled(addonId, enabled)
    }

    suspend fun uninstallAddon(addonId: String) {
        addonDao.deleteAddonById(addonId)
    }

    suspend fun clearExpiredCache() {
        catalogDao.clearExpired()
        metadataDao.clearExpired()
        streamDao.clearExpired()
    }
}
