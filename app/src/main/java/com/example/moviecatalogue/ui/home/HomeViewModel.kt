package com.example.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.vo.Resource

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTopMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getTopMovies()
    fun getTopTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = movieRepository.getTopTvShows()
    fun getFavorites(): LiveData<PagedList<FavoriteEntity>> = movieRepository.getFavorites()
}