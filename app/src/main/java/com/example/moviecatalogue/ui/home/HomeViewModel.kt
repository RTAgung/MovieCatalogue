package com.example.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.data.source.MovieRepository

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTrendingMovie(): LiveData<List<Movie>> = movieRepository.getTrendingMovie()
    fun getTrendingTvShow(): LiveData<List<TvShow>> = movieRepository.getTrendingTvShow()
}