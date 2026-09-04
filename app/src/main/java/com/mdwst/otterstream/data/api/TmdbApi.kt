package com.mdwst.otterstream.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): TmdbSearchResponse

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): TmdbMovieDetail

    @GET("tv/{id}")
    suspend fun getTvSeries(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): TmdbTvDetail

    @GET("tv/{id}/season/{season_number}/episode/{episode_number}")
    suspend fun getEpisode(
        @Path("id") id: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int,
        @Query("api_key") apiKey: String
    ): TmdbEpisodeDetail
}

@Serializable
data class TmdbSearchResponse(
    val page: Int,
    val results: List<TmdbSearchResult>,
    @SerialName("total_pages")
    val totalPages: Int
)

@Serializable
data class TmdbSearchResult(
    val id: Int,
    @SerialName("media_type")
    val mediaType: String,
    val title: String? = null,
    val name: String? = null,
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null
)

@Serializable
data class TmdbMovieDetail(
    val id: Int,
    val title: String,
    val overview: String? = null,
    val genres: List<TmdbGenre> = emptyList(),
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    val runtime: Int? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    val credits: TmdbCredits? = null
)

@Serializable
data class TmdbTvDetail(
    val id: Int,
    val name: String,
    val overview: String? = null,
    val genres: List<TmdbGenre> = emptyList(),
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int? = null,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    val credits: TmdbCredits? = null
)

@Serializable
data class TmdbEpisodeDetail(
    val id: Int,
    val name: String,
    val overview: String? = null,
    @SerialName("still_path")
    val stillPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("episode_number")
    val episodeNumber: Int
)

@Serializable
data class TmdbGenre(
    val id: Int,
    val name: String
)

@Serializable
data class TmdbCredits(
    val cast: List<TmdbCastMember> = emptyList(),
    val crew: List<TmdbCrewMember> = emptyList()
)

@Serializable
data class TmdbCastMember(
    val id: Int,
    val name: String,
    val character: String,
    @SerialName("profile_path")
    val profilePath: String? = null
)

@Serializable
data class TmdbCrewMember(
    val id: Int,
    val name: String,
    val job: String,
    @SerialName("profile_path")
    val profilePath: String? = null
)
