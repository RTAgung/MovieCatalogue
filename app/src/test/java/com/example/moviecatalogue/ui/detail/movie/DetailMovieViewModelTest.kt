package com.example.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/*
Scenario DetailMovieViewModelTest :
    - Memuat Detail MovieEntity
        - Memanipulasi data ketika pemanggilan data detail movie di kelas repository
        - Memastikan metode di kelas repository terpanggil
        - Memastikan data movie tidak null
        - Memastikan data movie sesuai dengan yang diharapkan
 */

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val dummyMovieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieEntityObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        dummyMovieId?.let { viewModel.setMovieId(it) }
    }

    @Test
    fun getMovie() {
        val movieLive = MutableLiveData<MovieEntity>()
        movieLive.value = dummyMovie

        if (dummyMovieId != null) {
            `when`(movieRepository.getMovie(dummyMovieId)).thenReturn(movieLive)
            val movie = viewModel.getMovie().value as MovieEntity
            verify(movieRepository).getMovie(dummyMovieId)

            assertNotNull(movie)
            assertEquals(dummyMovie.id, movie.id)
            assertEquals(dummyMovie.originalTitle, movie.originalTitle)
            assertEquals(dummyMovie.title, movie.title)
            assertEquals(dummyMovie.overview, movie.overview)
            assertEquals(dummyMovie.backdropPath, movie.backdropPath)
            assertEquals(dummyMovie.posterPath, movie.posterPath)
            assertEquals(dummyMovie.releaseDate, movie.releaseDate)
            assertEquals(dummyMovie.voteAverage, movie.voteAverage)
            assertEquals(dummyMovie.voteCount, movie.voteCount)
            assertEquals(dummyMovie.tagline, movie.tagline)
            assertEquals(dummyMovie.genres, movie.genres)
            assertEquals(dummyMovie.runtime, movie.runtime)

            viewModel.getMovie().observeForever(movieEntityObserver)
            Mockito.verify(movieEntityObserver).onChanged(dummyMovie)
        }
    }
}