package com.example.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("genres")
    val genres: List<TvShowGenresItem>,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,
)

data class TvShowGenresItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)