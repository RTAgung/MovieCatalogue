package com.example.moviecatalogue.ui.home

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/*
Scenario HomeViewModelTest :
    - Memuat Daftar Movie
        - Memastikan data movie tidak null
        - Memastikan jumlah data movie sesuai dengan yang diharapkan
        - Memastikan data movie pertama sesuai dengan yang diharapkan
    - Memuat Daftar Tv Show
        - Memastikan data tv show tidak null
        - Memastikan jumlah data tv show sesuai dengan yang diharapkan
        - Memastikan data tv show pertama sesuai dengan yang diharapkan
 */

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    private val dummyFirstMovie = DataDummy.generateDummyMovie()[0]
    private val dummyFirstTvShow = DataDummy.generateDummyTvShow()[0]

    private val dummyMovieSize = 10
    private val dummyTvShowSize = 10

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    @Test
    fun getAllMovie() {
        val movies = viewModel.getTrendingMovie()
        assertNotNull(movies)
        assertEquals(dummyFirstMovie, movies[0])
        assertEquals(dummyMovieSize, movies.size)
    }

    @Test
    fun getAllTvShow() {
        val tvShows = viewModel.getTrendingTvShow()
        assertNotNull(tvShows)
        assertEquals(dummyFirstTvShow, tvShows[0])
        assertEquals(dummyTvShowSize, tvShows.size)
    }
}