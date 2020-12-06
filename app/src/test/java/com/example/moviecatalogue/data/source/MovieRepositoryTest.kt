package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.example.moviecatalogue.utils.PagedListUtil
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieRepositoryTest {

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val topMovieResponses = DataDummy.generateRemoteDummyTopMovie()
    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponses.id

    private val topTvShowResponses = DataDummy.generateRemoteDummyTopTvShow()
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponses.id

    private val favoriteResponses = DataDummy.generateDummyFavorite()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun getTopMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getTopMovies()).thenReturn(dataSourceFactory)
        movieRepository.getTopMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateRemoteDummyTopMovie()))
        verify(local).getTopMovies()
        assertNotNull(movieEntities.data)
        assertEquals(topMovieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTopTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTopTvShows()).thenReturn(dataSourceFactory)
        movieRepository.getTopTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateRemoteDummyTopTvShow()))
        verify(local).getTopTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(topTvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyEntity = MutableLiveData<ApiResponse<MovieResponse>>()
        dummyEntity.value = ApiResponse.success(DataDummy.generateRemoteDummyMovie())

        `when`(remote.getMovie(movieId)).thenReturn(dummyEntity)
        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovie(movieId))
        verify(remote).getMovie(movieId)

        print(movieEntity.data)
        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.title)
        assertEquals(movieResponses.id, movieEntity.data?.id)
        assertEquals(movieResponses.title, movieEntity.data?.title)
        assertEquals(movieResponses.originalTitle, movieEntity.data?.originalTitle)
        assertEquals(movieResponses.voteAverage, movieEntity.data?.voteAverage)
        assertEquals(movieResponses.voteCount, movieEntity.data?.voteCount)
        assertEquals(movieResponses.backdropPath, movieEntity.data?.backdropPath)
    }

    @Test
    fun getTvShow() {
        val dummyEntity = MutableLiveData<ApiResponse<TvShowResponse>>()
        dummyEntity.value = ApiResponse.success(DataDummy.generateRemoteDummyTvShow())

        `when`(remote.getTvShow(tvShowId)).thenReturn(dummyEntity)
        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getTvShow(tvShowId))
        verify(remote).getTvShow(tvShowId)

        assertNotNull(tvShowEntity.data)
        assertNotNull(tvShowEntity.data?.name)
        assertEquals(tvShowResponses.id, tvShowEntity.data?.id)
        assertEquals(tvShowResponses.name, tvShowEntity.data?.name)
        assertEquals(tvShowResponses.originalName, tvShowEntity.data?.originalName)
        assertEquals(tvShowResponses.voteAverage, tvShowEntity.data?.voteAverage)
        assertEquals(tvShowResponses.voteCount, tvShowEntity.data?.voteCount)
        assertEquals(tvShowResponses.backdropPath, tvShowEntity.data?.backdropPath)
    }

    @Test
    fun getFavorites() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(local.getFavorites()).thenReturn(dataSourceFactory)
        movieRepository.getFavorites()

        val favoriteEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyFavorite()))
        verify(local).getFavorites()
        assertNotNull(favoriteEntities)
        assertEquals(favoriteResponses.size.toLong(), favoriteEntities.data?.size?.toLong())
    }
}