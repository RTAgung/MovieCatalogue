package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.source.remote.CallbackApiListener
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.TrendingMovieResponse
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val trendingMovieResponses = DataDummy.generateDummyMovie()
    private val movieResponses = DataDummy.generateDummyMovie()[0]
    private val movieId = movieResponses.id
    private val trendingTvShowResponses = DataDummy.generateDummyTvShow()
    private val tvShowResponses = DataDummy.generateDummyTvShow()[0]
    private val tvShowId = tvShowResponses.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getTrendingMovie(){
        doAnswer { invocation ->
            (invocation.arguments[0] as CallbackApiListener<TrendingMovieResponse>)
                .onSuccess(trendingMovieResponses)
            null
        }.`when`(remote).getTrendingMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(mainRepository.getMovieList())
        verify(remote).getMovieList(any())
        assertNotNull(movieEntities)
        assertEquals(movieListResponses.size.toLong(), movieEntities.size.toLong())
    }
}