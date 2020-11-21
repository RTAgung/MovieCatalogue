package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.remote.response.MovieGenresItem
import com.example.moviecatalogue.data.source.remote.response.TvShowGenresItem

object Helper {
    fun getMovieGenres(responseGenre: List<MovieGenresItem>): String {
        val listGenres = ArrayList<String>()
        for (genre in responseGenre) {
            listGenres.add(genre.name)
        }
        return genresFormatting(listGenres)
    }

    fun getTvShowGenres(responseGenre: List<TvShowGenresItem>): String {
        val listGenres = ArrayList<String>()
        for (genre in responseGenre) {
            listGenres.add(genre.name)
        }
        return genresFormatting(listGenres)
    }

    fun runtimeFormatting(time: Int): String {
        val hours = time / 60
        val minutes = time % 60
        return when {
            hours > 0 -> "${hours}h ${minutes}m"
            else -> "${minutes}m"
        }
    }

    fun genresFormatting(listGenres: List<String>): String {
        var genres = ""
        for (i in listGenres.indices) {
            genres = if (i == 0) listGenres[i] else "$genres, ${listGenres[i]}"
        }
        return genres
    }
}