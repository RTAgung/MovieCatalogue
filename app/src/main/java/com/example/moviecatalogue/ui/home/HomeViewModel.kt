package com.example.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTrendingMovie(): LiveData<List<MovieEntity>> = movieRepository.getTopMovies()
    fun getTrendingTvShow(): LiveData<List<TvShowEntity>> = movieRepository.getTopTvShows()
}