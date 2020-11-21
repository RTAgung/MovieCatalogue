package com.example.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TopTvShowResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TopTvShowResultsItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class TopTvShowResultsItem(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: Any,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("vote_count")
	val voteCount: Int
)
