package com.example.moviecatalogue.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.FakeMovieRepository
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
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
    private val dummyFirstFavorite = DataDummy.generateDummyFavorite()[0]

    private val dummyMovieSize = 20
    private val dummyTvShowSize = 20
    private val dummyFavoriteSize = 5

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieEntityObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var tvShowEntityObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var favoriteEntityObserver: Observer<PagedList<FavoriteEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShowEntity>

    @Mock
    private lateinit var pagedListFavorite: PagedList<FavoriteEntity>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(movieRepository)
    }

    @Test
    fun getTopMovie() {
//        val dummyMovies = DataDummy.generateDummyMovie()
//        val moviesLive = MutableLiveData<List<MovieEntity>>()
//        moviesLive.value = dummyMovies
//
//        `when`(movieRepository.getTopMovies()).thenReturn(moviesLive)
//        val movies = viewModel.getTopMovies().value
//        verify(movieRepository).getTopMovies()
//        assertNotNull(movies)
//        assertEquals(dummyFirstMovie, movies?.get(0))
//        assertEquals(dummyMovieSize, movies?.size)
//
//        viewModel.getTopMovies().observeForever(movieEntityObserver)
//        verify(movieEntityObserver).onChanged(dummyMovies)

        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(dummyMovieSize)
        `when`(dummyMovies.data?.get(0)).thenReturn(dummyFirstMovie)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getTopMovies()).thenReturn(movies)
        val movieEntities = viewModel.getTopMovies().value?.data
        verify(movieRepository).getTopMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyFirstMovie, movieEntities?.get(0))
        assertEquals(dummyMovieSize, movieEntities?.size)

        viewModel.getTopMovies().observeForever(movieEntityObserver)
        verify(movieEntityObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTopTvShow() {
//        val dummyTvShows = DataDummy.generateDummyTvShow()
//        val tvShowsLive = MutableLiveData<List<TvShowEntity>>()
//        tvShowsLive.value = dummyTvShows
//
//        `when`(movieRepository.getTopTvShows()).thenReturn(tvShowsLive)
//        val tvShows = viewModel.getTopTvShows().value
//        verify(movieRepository).getTopTvShows()
//        assertNotNull(tvShows)
//        assertEquals(dummyFirstTvShow, tvShows?.get(0))
//        assertEquals(dummyTvShowSize, tvShows?.size)
//
//        viewModel.getTopTvShows().observeForever(tvShowEntityObserver)
//        verify(tvShowEntityObserver).onChanged(dummyTvShows)

        val dummyTvShows = Resource.success(pagedListTvShow)
        `when`(dummyTvShows.data?.size).thenReturn(dummyTvShowSize)
        `when`(dummyTvShows.data?.get(0)).thenReturn(dummyFirstTvShow)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(movieRepository.getTopTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTopTvShows().value?.data
        verify(movieRepository).getTopTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(dummyFirstTvShow, tvShowEntities?.get(0))
        assertEquals(dummyTvShowSize, tvShowEntities?.size)

        viewModel.getTopTvShows().observeForever(tvShowEntityObserver)
        verify(tvShowEntityObserver).onChanged(dummyTvShows)
    }

    @Test
    fun getFavorite() {
        val dummyFavorites = pagedListFavorite
        `when`(dummyFavorites.size).thenReturn(dummyFavoriteSize)
        `when`(dummyFavorites[0]).thenReturn(dummyFirstFavorite)
        val favorites = MutableLiveData<PagedList<FavoriteEntity>>()
        favorites.value = dummyFavorites

        `when`(movieRepository.getFavorites()).thenReturn(favorites)
        val favoriteEntities = viewModel.getFavorites().value
        verify(movieRepository).getFavorites()
        assertNotNull(favoriteEntities)
        assertEquals(dummyFirstFavorite, favoriteEntities?.get(0))
        assertEquals(dummyFavoriteSize, favoriteEntities?.size)

        viewModel.getFavorites().observeForever(favoriteEntityObserver)
        verify(favoriteEntityObserver).onChanged(dummyFavorites)
    }
}