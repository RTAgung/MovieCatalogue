package com.example.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TopMovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TopMovieResultsItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class TopMovieResultsItem(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("vote_count")
	val voteCount: Int
)
