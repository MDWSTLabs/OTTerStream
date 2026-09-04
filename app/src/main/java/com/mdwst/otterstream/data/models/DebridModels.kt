package com.mdwst.otterstream.data.models

import kotlinx.serialization.Serializable

// Real-Debrid Models
@Serializable
data class RealDebridUserData(
    val id: Int,
    val username: String,
    val email: String,
    val points: Int,
    val locale: String,
    val avatar: String,
    val type: String,
    val premium: Int,
    val expiration: String
)

@Serializable
data class RealDebridTorrent(
    val id: String,
    val filename: String,
    val original_filename: String,
    val hash: String,
    val bytes: Long,
    val host: String,
    val split: Int,
    val progress: Int,
    val status: String,
    val added: String,
    val links: List<String>,
    val expires: String,
    val download: String? = null,
    val streamable: Int
)

// Premiumize Models
@Serializable
data class PremiumizeTransfer(
    val id: String,
    val name: String,
    val filename: String,
    val filesize: Long,
    val created_at: Long,
    val finished_at: Long? = null,
    val progress_percentage: Int,
    val status: String,
    val message: String? = null
)

// AllDebrid Models
@Serializable
data class AllDebridMagnet(
    val magnet_id: Int,
    val name: String,
    val hash: String,
    val size: Long,
    val status_code: Int,
    val status: String,
    val notified: Boolean,
    val links: List<AllDebridLink>? = null
)

@Serializable
data class AllDebridLink(
    val link: String,
    val filename: String,
    val size: Long
)
