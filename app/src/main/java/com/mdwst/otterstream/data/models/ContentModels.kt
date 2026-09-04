package com.mdwst.otterstream.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// Content type enumeration
enum class ContentType {
    MOVIE,
    SERIES,
    TV,
    SPORTS,
    UNKNOWN
}

// User's content library
@Entity(tableName = "watchlist")
data class WatchlistItem(
    @PrimaryKey
    val id: String,
    val title: String,
    val type: ContentType,
    val poster: String? = null,
    val description: String? = null,
    val addedAt: Long = System.currentTimeMillis(),
    val source: String, // addon ID or IPTV
    val metaData: String? = null // JSON serialized MetaItem
)

@Entity(tableName = "continue_watching")
data class ContinueWatchingItem(
    @PrimaryKey
    val id: String,
    val title: String,
    val poster: String? = null,
    val progress: Long, // milliseconds
    val duration: Long, // milliseconds
    val lastWatched: Long = System.currentTimeMillis(),
    val source: String,
    val streamUrl: String? = null,
    val subtitles: String? = null // JSON serialized List<Subtitle>
)

// Search results aggregation
data class SearchResult(
    val query: String,
    val results: List<MergedCatalogItem>
)

// Playback state
data class PlaybackState(
    val contentId: String,
    val title: String,
    val streamUrl: String,
    val currentPosition: Long = 0,
    val duration: Long = 0,
    val subtitles: List<Subtitle>? = null,
    val selectedSubtitle: Subtitle? = null
)
