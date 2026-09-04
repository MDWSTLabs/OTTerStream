package com.mdwst.otterstream.data.api

import com.mdwst.otterstream.data.models.RealDebridTorrent
import com.mdwst.otterstream.data.models.RealDebridUserData
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// Real-Debrid API
interface RealDebridApi {
    @GET("user")
    suspend fun getUser(
        @Header("Authorization") authorization: String
    ): RealDebridUserData

    @GET("torrents")
    suspend fun getTorrents(
        @Header("Authorization") authorization: String,
        @Query("limit") limit: Int = 200,
        @Query("offset") offset: Int = 0
    ): List<RealDebridTorrent>

    @GET("torrents/{id}")
    suspend fun getTorrent(
        @Path("id") id: String,
        @Header("Authorization") authorization: String
    ): RealDebridTorrent

    @POST("torrents/addMagnet")
    suspend fun addMagnet(
        @Query("magnet") magnet: String,
        @Header("Authorization") authorization: String
    ): RealDebridMagnetResponse

    @POST("torrents/{id}/selectFiles")
    suspend fun selectFiles(
        @Path("id") id: String,
        @Query("files") files: String,
        @Header("Authorization") authorization: String
    ): Void
}

@Serializable
data class RealDebridMagnetResponse(
    val id: String,
    val uri: String
)

// Premiumize API
interface PremiumizeApi {
    @POST("transfer/create")
    suspend fun createTransfer(
        @Query("apikey") apiKey: String,
        @Query("src") source: String,
        @Query("folder_id") folderId: String? = null
    ): PremiumizeTransferResponse

    @GET("transfer/list")
    suspend fun listTransfers(
        @Query("apikey") apiKey: String,
        @Query("limit") limit: Int = 100
    ): PremiumizeTransfersResponse
}

@Serializable
data class PremiumizeTransferResponse(
    val status: String,
    val result: PremiumizeTransferData?
)

@Serializable
data class PremiumizeTransferData(
    val id: String,
    val name: String,
    val message: String? = null
)

@Serializable
data class PremiumizeTransfersResponse(
    val status: String,
    val result: List<PremiumizeTransferItem> = emptyList()
)

@Serializable
data class PremiumizeTransferItem(
    val id: String,
    val name: String,
    val created: Long,
    val status: String
)

// AllDebrid API
interface AllDebridApi {
    @POST("magnet/upload")
    suspend fun uploadMagnet(
        @Query("apikey") apiKey: String,
        @Query("magnet") magnet: String
    ): AllDebridMagnetResponse

    @GET("magnet/status")
    suspend fun getMagnetStatus(
        @Query("apikey") apiKey: String,
        @Query("id") magnetId: Int
    ): AllDebridMagnetStatusResponse
}

@Serializable
data class AllDebridMagnetResponse(
    val status: String,
    val data: AllDebridMagnetData?
)

@Serializable
data class AllDebridMagnetData(
    val magnet: AllDebridMagnetInfo?
)

@Serializable
data class AllDebridMagnetInfo(
    val id: Int,
    val name: String,
    val hash: String,
    val size: Long,
    val status: String
)

@Serializable
data class AllDebridMagnetStatusResponse(
    val status: String,
    val data: AllDebridMagnetStatusData?
)

@Serializable
data class AllDebridMagnetStatusData(
    val magnets: List<AllDebridMagnetStatus> = emptyList()
)

@Serializable
data class AllDebridMagnetStatus(
    val id: Int,
    val name: String,
    val status: String,
    val links: List<String> = emptyList()
)
