package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId: String
    private var movie: Movie? = null

    fun setMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun setMovie() {
        val listMovies = DataDummy.generateDummyMovie()
        for (movie in listMovies) {
            if (movie.id == movieId)
                this.movie = movie
        }
    }

    fun getMovie(): Movie? {
        if (movie == null)
            setMovie()
        return movie
    }
}