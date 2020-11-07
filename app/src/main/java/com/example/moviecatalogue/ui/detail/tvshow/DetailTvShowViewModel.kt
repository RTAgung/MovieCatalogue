package com.example.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.data.source.MovieRepository

class DetailTvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var tvShowId: String

    fun setTvShowId(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<TvShow> = movieRepository.getTvShow(tvShowId)
}