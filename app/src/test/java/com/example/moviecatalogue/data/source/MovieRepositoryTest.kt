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

@RunWith(MockitoJUnitRunner::class)
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
//        doAnswer { invocation ->
//            (invocation.arguments[0] as CallbackApiListener<List<TrendingMovieResultsItem>>)
//                .onSuccess(trendingMovieResponses)
//            null
//        }.`when`(remote).getTopMovies(any())
//        val movies = LiveDataTestUtil.getValue(movieRepository.getTopMovies())
//        verify(remote).getTopMovies(any())
//        assertNotNull(movies)
//        assertEquals(trendingMovieResponses.size.toLong(), movies.size.toLong())

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
//        doAnswer { invocation ->
//            (invocation.arguments[0] as CallbackApiListener<List<TrendingTvShowResultsItem>>)
//                .onSuccess(topTvShowResponses)
//            null
//        }.`when`(remote).getTopTvShows(any())
//        val tvShows = LiveDataTestUtil.getValue(movieRepository.getTopTvShows())
//        verify(remote).getTopTvShows(any())
//        assertNotNull(tvShows)
//        assertEquals(topTvShowResponses.size.toLong(), tvShows.size.toLong())

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
//        doAnswer { invocation ->
//            (invocation.arguments[1] as CallbackApiListener<MovieResponse>)
//                .onSuccess(movieResponses)
//            null
//        }.`when`(remote).getMovie(eq(movieId), any())
//
//        val movie =
//            LiveDataTestUtil.getValue(movieRepository.getMovie(movieId))
//
//        verify(remote).getMovie(eq(movieId), any())
//
//        assertNotNull(movie)
//        assertEquals(movieResponses.id, movie.id)
//        assertEquals(movieResponses.title, movie.title)
//        assertEquals(movieResponses.originalTitle, movie.originalTitle)
//        assertEquals(movieResponses.voteAverage, movie.voteAverage)
//        assertEquals(movieResponses.voteCount, movie.voteCount)
//        assertEquals(movieResponses.backdropPath, movie.backdropPath)

        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyMovie()[0]

        `when`(local.getMovieById(movieId)).thenReturn(dummyEntity)
        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovie(movieId))
        verify(local).getMovieById(movieId)

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
//        doAnswer { invocation ->
//            (invocation.arguments[1] as CallbackApiListener<TvShowResponse>)
//                .onSuccess(tvShowResponses)
//            null
//        }.`when`(remote).getTvShow(eq(tvShowId), any())
//
//        val tvShow =
//            LiveDataTestUtil.getValue(movieRepository.getTvShow(tvShowId))
//
//        verify(remote).getTvShow(eq(tvShowId), any())
//
//        assertNotNull(tvShow)
//        assertEquals(tvShowResponses.id, tvShow.id)
//        assertEquals(tvShowResponses.name, tvShow.name)
//        assertEquals(tvShowResponses.originalName, tvShow.originalName)
//        assertEquals(tvShowResponses.voteAverage, tvShow.voteAverage)
//        assertEquals(tvShowResponses.voteCount, tvShow.voteCount)
//        assertEquals(tvShowResponses.backdropPath, tvShow.backdropPath)

        val dummyEntity = MutableLiveData<ApiResponse<TvShowResponse>>()
        dummyEntity.value = ApiResponse.success(DataDummy.generateRemoteDummyTvShow())

        `when`(remote.getTvShow(tvShowId)).thenReturn(dummyEntity)
        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getTvShow(tvShowId))
//        val tvShowEntity = movieRepository.getTvShow(tvShowId).value
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