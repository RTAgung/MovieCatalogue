package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailActivityViewModelTest {
    private lateinit var viewModel: DetailActivityViewModel

    private val dummyMovieEntity = DataDummy.generateDummyMovie()[0]
    private val dummyMovieId = dummyMovieEntity.id
    private val dummyTvShowEntity = DataDummy.generateDummyTvShow()[0]
    private val dummyTvShowId = dummyTvShowEntity.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieEntityObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowEntityObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = DetailActivityViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        viewModel.setId(dummyMovieId)

//        val movieLive = MutableLiveData<MovieEntity>()
//        movieLive.value = dummyMovie
//
//        if (dummyMovieId != null) {
//            Mockito.`when`(movieRepository.getMovie(dummyMovieId)).thenReturn(movieLive)
//            val movie = viewModel.getMovie().value as MovieEntity
//            verify(movieRepository).getMovie(dummyMovieId)
//
//            assertNotNull(movie)
//            assertEquals(dummyMovie.id, movie.id)
//            assertEquals(dummyMovie.originalTitle, movie.originalTitle)
//            assertEquals(dummyMovie.title, movie.title)
//            assertEquals(dummyMovie.overview, movie.overview)
//            assertEquals(dummyMovie.backdropPath, movie.backdropPath)
//            assertEquals(dummyMovie.posterPath, movie.posterPath)
//            assertEquals(dummyMovie.releaseDate, movie.releaseDate)
//            assertEquals(dummyMovie.voteAverage, movie.voteAverage)
//            assertEquals(dummyMovie.voteCount, movie.voteCount)
//            assertEquals(dummyMovie.tagline, movie.tagline)
//            assertEquals(dummyMovie.genres, movie.genres)
//            assertEquals(dummyMovie.runtime, movie.runtime)
//
//            viewModel.getMovie().observeForever(movieEntityObserver)
//            Mockito.verify(movieEntityObserver).onChanged(dummyMovie)
//        }

        val dummyMovie = Resource.success(dummyMovieEntity)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(movieRepository.getMovie(dummyMovieId)).thenReturn(movie)
//        val movieEntity = viewModel.movie.value?.data
//        print(movieEntity)
//        verify(movieRepository).getMovie(dummyMovieId)
//        assertNotNull(movieEntity)
//        assertEquals(dummyMovieEntity.id, movieEntity?.id)
//        assertEquals(dummyMovieEntity.originalTitle, movieEntity?.originalTitle)
//        assertEquals(dummyMovieEntity.title, movieEntity?.title)

        viewModel.movie.observeForever(movieEntityObserver)
        verify(movieEntityObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() {
        viewModel.setId(dummyTvShowId)

//        val tvShowLive = MutableLiveData<TvShowEntity>()
//        tvShowLive.value = dummyTvShowEntity
//
//        if (dummyTvShowId != null) {
//            Mockito.`when`(movieRepository.getTvShow(dummyTvShowId)).thenReturn(tvShowLive)
//            val tvShow = viewModel.getTvShow().value as TvShowEntity
//            verify(movieRepository).getTvShow(dummyTvShowId)
//
//            assertNotNull(tvShow)
//            assertEquals(dummyTvShowEntity.id, tvShow.id)
//            assertEquals(dummyTvShowEntity.originalName, tvShow.originalName)
//            assertEquals(dummyTvShowEntity.name, tvShow.name)
//            assertEquals(dummyTvShowEntity.overview, tvShow.overview)
//            assertEquals(dummyTvShowEntity.backdropPath, tvShow.backdropPath)
//            assertEquals(dummyTvShowEntity.posterPath, tvShow.posterPath)
//            assertEquals(dummyTvShowEntity.firstAirDate, tvShow.firstAirDate)
//            assertEquals(dummyTvShowEntity.voteAverage, tvShow.voteAverage)
//            assertEquals(dummyTvShowEntity.voteCount, tvShow.voteCount)
//            assertEquals(dummyTvShowEntity.numberOfEpisodes, tvShow.numberOfEpisodes)
//            assertEquals(dummyTvShowEntity.numberOfSeasons, tvShow.numberOfSeasons)
//            assertEquals(dummyTvShowEntity.genres, tvShow.genres)
//
//            viewModel.getTvShow().observeForever(tvShowEntityObserver)
//            Mockito.verify(tvShowEntityObserver).onChanged(dummyTvShowEntity)
//        }

        val dummyTvShow = Resource.success(dummyTvShowEntity)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getTvShow(dummyTvShowId)).thenReturn(tvShow)
//        val tvShowEntity = viewModel.tvShow.value?.data
//        verify(movieRepository).getTvShow(dummyTvShowId)
//        assertNotNull(tvShowEntity)
//        assertEquals(dummyTvShowEntity.id, tvShowEntity?.id)
//        assertEquals(dummyTvShowEntity.originalName, tvShowEntity?.originalName)
//        assertEquals(dummyTvShowEntity.name, tvShowEntity?.name)

        viewModel.tvShow.observeForever(tvShowEntityObserver)
        verify(tvShowEntityObserver).onChanged(dummyTvShow)
    }
}