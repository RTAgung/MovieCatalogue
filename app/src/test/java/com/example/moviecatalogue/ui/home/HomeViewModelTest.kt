package com.example.moviecatalogue.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/*
Scenario HomeViewModelTest :
    - Memuat Daftar MovieEntity
        - Memanipulasi data ketika pemanggilan data movie di kelas repository
        - Memastikan metode di kelas repository terpanggil
        - Memastikan data movie tidak null
        - Memastikan jumlah data movie sesuai dengan yang diharapkan
        - Memastikan data movie pertama sesuai dengan yang diharapkan
    - Memuat Daftar Tv Show
        - Memanipulasi data ketika pemanggilan data tv show di kelas repository
        - Memastikan metode di kelas repository terpanggil
        - Memastikan data tv show tidak null
        - Memastikan jumlah data tv show sesuai dengan yang diharapkan
        - Memastikan data tv show pertama sesuai dengan yang diharapkan
 */

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    private val dummyFirstMovie = DataDummy.generateDummyMovie()[0]
    private val dummyFirstTvShow = DataDummy.generateDummyTvShow()[0]

    private val dummyMovieSize = 10
    private val dummyTvShowSize = 10

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieEntityObserver: Observer<List<MovieEntity>>

    @Mock
    private lateinit var tvShowEntityObserver: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(movieRepository)
    }

    @Test
    fun getTrendingMovie() {
        val dummyMovies = DataDummy.generateDummyMovie()
        val moviesLive = MutableLiveData<List<MovieEntity>>()
        moviesLive.value = dummyMovies

        `when`(movieRepository.getTopMovies()).thenReturn(moviesLive)
        val movies = viewModel.getTopMovies().value
        verify(movieRepository).getTopMovies()
        assertNotNull(movies)
        assertEquals(dummyFirstMovie, movies?.get(0))
        assertEquals(dummyMovieSize, movies?.size)

        viewModel.getTopMovies().observeForever(movieEntityObserver)
        verify(movieEntityObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTrendingTvShow() {
        val dummyTvShows = DataDummy.generateDummyTvShow()
        val tvShowsLive = MutableLiveData<List<TvShowEntity>>()
        tvShowsLive.value = dummyTvShows

        `when`(movieRepository.getTopTvShows()).thenReturn(tvShowsLive)
        val tvShows = viewModel.getTopTvShows().value
        verify(movieRepository).getTopTvShows()
        assertNotNull(tvShows)
        assertEquals(dummyFirstTvShow, tvShows?.get(0))
        assertEquals(dummyTvShowSize, tvShows?.size)

        viewModel.getTopTvShows().observeForever(tvShowEntityObserver)
        verify(tvShowEntityObserver).onChanged(dummyTvShows)
    }
}