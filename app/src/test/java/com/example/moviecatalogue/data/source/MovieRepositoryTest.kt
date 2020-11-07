package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.source.remote.CallbackApiListener
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TrendingMovieResultsItem
import com.example.moviecatalogue.data.source.remote.response.TrendingTvShowResultsItem
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val trendingMovieResponses = DataDummy.generateRemoteDummyTrendingMovie()
    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponses.id
    private val trendingTvShowResponses = DataDummy.generateRemoteDummyTrendingTvShow()
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponses.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getTrendingMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as CallbackApiListener<List<TrendingMovieResultsItem>>)
                .onSuccess(trendingMovieResponses)
            null
        }.`when`(remote).getTrendingMovie(any())
        val movies = LiveDataTestUtil.getValue(movieRepository.getTrendingMovie())
        verify(remote).getTrendingMovie(any())
        assertNotNull(movies)
        assertEquals(trendingMovieResponses.size.toLong(), movies.size.toLong())
    }

    @Test
    fun getTrendingTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as CallbackApiListener<List<TrendingTvShowResultsItem>>)
                .onSuccess(trendingTvShowResponses)
            null
        }.`when`(remote).getTrendingTvShow(any())
        val tvShows = LiveDataTestUtil.getValue(movieRepository.getTrendingTvShow())
        verify(remote).getTrendingTvShow(any())
        assertNotNull(tvShows)
        assertEquals(trendingTvShowResponses.size.toLong(), tvShows.size.toLong())
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as CallbackApiListener<MovieResponse>)
                .onSuccess(movieResponses)
            null
        }.`when`(remote).getMovie(eq(movieId), any())

        val movie =
            LiveDataTestUtil.getValue(movieRepository.getMovie(movieId))

        verify(remote).getMovie(eq(movieId), any())

        assertNotNull(movie)
        assertEquals(movieResponses.id, movie.id)
        assertEquals(movieResponses.title, movie.title)
        assertEquals(movieResponses.originalTitle, movie.originalTitle)
        assertEquals(movieResponses.voteAverage, movie.voteAverage)
        assertEquals(movieResponses.voteCount, movie.voteCount)
        assertEquals(movieResponses.genres, movie.genres)
        assertEquals(movieResponses.backdropPath, movie.backdropPath)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as CallbackApiListener<TvShowResponse>)
                .onSuccess(tvShowResponses)
            null
        }.`when`(remote).getTvShow(eq(tvShowId), any())

        val tvShow =
            LiveDataTestUtil.getValue(movieRepository.getTvShow(tvShowId))

        verify(remote).getTvShow(eq(tvShowId), any())

        assertNotNull(tvShow)
        assertEquals(tvShowResponses.id, tvShow.id)
        assertEquals(tvShowResponses.name, tvShow.name)
        assertEquals(tvShowResponses.originalName, tvShow.originalName)
        assertEquals(tvShowResponses.voteAverage, tvShow.voteAverage)
        assertEquals(tvShowResponses.voteCount, tvShow.voteCount)
        assertEquals(tvShowResponses.genres, tvShow.genres)
        assertEquals(tvShowResponses.backdropPath, tvShow.backdropPath)
    }
}