package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.remote.response.MovieGenresItem
import com.example.moviecatalogue.data.source.remote.response.TvShowGenresItem

object Helper {
    fun getMovieGenres(responseGenre: List<MovieGenresItem>): List<String> {
        val listGenres = ArrayList<String>()
        for (genre in responseGenre) {
            listGenres.add(genre.name)
        }
        return listGenres
    }

    fun getTvShowGenres(responseGenre: List<TvShowGenresItem>): List<String> {
        val listGenres = ArrayList<String>()
        for (genre in responseGenre) {
            listGenres.add(genre.name)
        }
        return listGenres
    }

    fun runtimeFormatting(time: Int): String {
        val hours = time / 60;
        val minutes = time % 60;
        return when {
            hours > 0 -> "${hours}h ${minutes}m"
            else -> "${minutes}m"
        }
    }

    fun genresFormatting(listGenres: List<String>): String {
        var genres = "";
        for (itemGenre in listGenres) {
            genres = "$genres, $itemGenre"
        }
        return genres
    }
}