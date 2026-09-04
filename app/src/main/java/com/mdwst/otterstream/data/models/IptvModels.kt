package com.mdwst.otterstream.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// M3U Playlist Models
data class M3UPlaylist(
    val channels: List<IptvChannel>
)

data class IptvChannelData(
    val id: String,
    val name: String,
    val logo: String? = null,
    val group: String? = null,
    val url: String,
    val epgId: String? = null
)

// EPG Models
data class EpgProgram(
    val channel: String,
    val start: Long,
    val stop: Long,
    val title: String,
    val description: String? = null,
    val category: String? = null,
    val icon: String? = null,
    val rating: String? = null
)

// Xtream Codes Models
data class XtreamCategory(
    val categoryId: String,
    val categoryName: String,
    val parentId: String? = null
)

data class XtreamStream(
    val id: String,
    val name: String,
    val categoryId: String,
    val streamIcon: String? = null,
    val streamUrl: String
)
