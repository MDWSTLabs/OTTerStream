package com.mdwst.otterstream.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Stremio Addon Manifest
@Serializable
data class AddonManifest(
    val id: String,
    val version: String,
    val name: String,
    val description: String? = null,
    val logo: String? = null,
    val resources: List<Resource>,
    val types: List<String>,
    val catalogs: List<Catalog>,
    @SerialName("contactEmail")
    val contactEmail: String? = null,
    @SerialName("behaviorHints")
    val behaviorHints: BehaviorHints? = null
)

@Serializable
data class Resource(
    val name: String,
    val types: List<String>,
    val idPrefixes: List<String>? = null
)

@Serializable
data class Catalog(
    val type: String,
    val id: String,
    val name: String,
    @SerialName("extra")
    val extra: List<CatalogExtra>? = null
)

@Serializable
data class CatalogExtra(
    val name: String,
    val isRequired: Boolean? = null,
    val options: List<String>? = null
)

@Serializable
data class BehaviorHints(
    val p2p: Boolean? = null,
    @SerialName("configurable")
    val configurable: Boolean? = null,
    @SerialName("configurationRequired")
    val configurationRequired: Boolean? = null
)

// Stremio Catalog Response
@Serializable
data class CatalogResponse(
    val metas: List<MetaItem> = emptyList()
)

// Stremio Meta Item
@Serializable
data class MetaItem(
    val id: String,
    val type: String,
    val name: String,
    val poster: String? = null,
    val background: String? = null,
    val logo: String? = null,
    val description: String? = null,
    val releaseInfo: String? = null,
    val runtime: String? = null,
    val rating: Double? = null,
    val director: List<String>? = null,
    val cast: List<String>? = null,
    val imdbRating: String? = null,
    val genres: List<String>? = null,
    val links: List<Link>? = null,
    val trailers: List<Trailer>? = null,
    @SerialName("behaviorHints")
    val behaviorHints: MetaBehaviorHints? = null
)

@Serializable
data class Link(
    val name: String,
    val url: String,
    val category: String? = null
)

@Serializable
data class Trailer(
    val source: String,
    val type: String? = null
)

@Serializable
data class MetaBehaviorHints(
    @SerialName("defaultVideoId")
    val defaultVideoId: String? = null,
    @SerialName("proxyHeaders")
    val proxyHeaders: Map<String, String>? = null
)

// Stremio Meta Detailed Response
@Serializable
data class MetaDetailResponse(
    val meta: MetaItem
)

// Stremio Stream Response
@Serializable
data class StreamResponse(
    val streams: List<Stream> = emptyList()
)

// Stremio Stream
@Serializable
data class Stream(
    val url: String? = null,
    val title: String? = null,
    val quality: String? = null,
    val sources: List<String>? = null,
    @SerialName("behaviorHints")
    val behaviorHints: StreamBehaviorHints? = null,
    val subtitles: List<Subtitle>? = null,
    @SerialName("externalUrl")
    val externalUrl: String? = null
)

@Serializable
data class StreamBehaviorHints(
    @SerialName("bingeGroup")
    val bingeGroup: String? = null,
    @SerialName("proxyHeaders")
    val proxyHeaders: Map<String, String>? = null
)

@Serializable
data class Subtitle(
    val lang: String,
    val url: String
)
