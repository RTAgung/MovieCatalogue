package com.example.moviecatalogue.data

data class Movie(
    var id: String? = null,
    var originalTitle: String? = null,
    var title: String? = null,
    var tagline: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
    var runtime: Int? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null,
    var genres: List<String>? = null
)