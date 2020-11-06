package com.example.moviecatalogue.ui.detail.movie

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/*
Scenario DetailMovieViewModelTest :
    - Memuat Detail Movie
        - Memastikan data movie tidak null
        - Memastikan data movie sesuai dengan yang diharapkan
 */

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val dummyMovieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        dummyMovieId?.let { viewModel.setMovieId(it) }
    }

    @Test
    fun getDetailMovie() {
        dummyMovie.id?.let { viewModel.setMovieId(it) }
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie?.id)
        assertEquals(dummyMovie.originalTitle, movie?.originalTitle)
        assertEquals(dummyMovie.title, movie?.title)
        assertEquals(dummyMovie.overview, movie?.overview)
        assertEquals(dummyMovie.backdropPath, movie?.backdropPath)
        assertEquals(dummyMovie.posterPath, movie?.posterPath)
        assertEquals(dummyMovie.releaseDate, movie?.releaseDate)
        assertEquals(dummyMovie.voteAverage, movie?.voteAverage)
    }
}