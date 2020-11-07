package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.data.source.MovieRepository

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var movieId: String

    fun setMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<Movie> = movieRepository.getMovie(movieId)
}