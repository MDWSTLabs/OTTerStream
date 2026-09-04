package com.mdwst.otterstream.data.api

import com.mdwst.otterstream.data.models.AddonManifest
import com.mdwst.otterstream.data.models.CatalogResponse
import com.mdwst.otterstream.data.models.MetaDetailResponse
import com.mdwst.otterstream.data.models.StreamResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

// Generic Stremio Addon API
interface StremioAddonApi {
    @GET
    suspend fun getManifest(@Url url: String): AddonManifest

    @GET("{path}")
    suspend fun getCatalog(
        @Url baseUrl: String,
        @Path("path") path: String
    ): CatalogResponse

    @GET("{path}")
    suspend fun getMeta(
        @Url baseUrl: String,
        @Path("path") path: String
    ): MetaDetailResponse

    @GET("{path}")
    suspend fun getStreams(
        @Url baseUrl: String,
        @Path("path") path: String
    ): StreamResponse
}
