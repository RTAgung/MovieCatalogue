package com.example.moviecatalogue.data

data class TvShow(
    var id: String? = null,
    var name: String? = null,
    var originalName: String? = null,
    var overview: String? = null,
    var firstAirDate: String? = null,
    var numberOfEpisodes: Int? = null,
    var numberOfSeasons: Int? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
    var backdropPath: String? = null,
    var posterPath: String? = null,
    var genres: List<String>? = null
)