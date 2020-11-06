package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.data.TvShow

interface MovieDataSource {
    fun getTrendingMovie(): LiveData<List<Movie>>
    fun getTrendingTvShow(): LiveData<List<TvShow>>
    fun getMovie(movieId: Int): LiveData<Movie>
    fun getTvShow(tvShowId: Int): LiveData<TvShow>
}