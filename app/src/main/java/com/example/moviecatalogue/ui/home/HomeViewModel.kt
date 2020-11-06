package com.example.moviecatalogue.ui.home

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.utils.DataDummy

class HomeViewModel : ViewModel() {
    private var listMovies = ArrayList<Movie>()
    private var listTvShows = ArrayList<TvShow>()

    fun setAllMovie() {
        listMovies = DataDummy.generateDummyMovie()
    }

    fun getAllMovie(): ArrayList<Movie> {
        if (listMovies.size == 0) {
            setAllMovie()
        }
        return listMovies
    }

    fun setAllTvShow() {
        listTvShows = DataDummy.generateDummyTvShow()
    }

    fun getAllTvShow(): ArrayList<TvShow> {
        if (listTvShows.size == 0) {
            setAllTvShow()
        }
        return listTvShows
    }
}