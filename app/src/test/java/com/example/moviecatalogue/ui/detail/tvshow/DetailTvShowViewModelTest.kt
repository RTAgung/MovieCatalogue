package com.example.moviecatalogue.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.TvShow
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
Scenario DetailTvShowViewModelTest :
    - Memuat Detail Tv Show
        - Memastikan data tv show tidak null
        - Memastikan data tv show sesuai dengan yang diharapkan
 */

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvShowObserver: Observer<TvShow>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(movieRepository)
        dummyTvShowId?.let { viewModel.setTvShowId(it) }
    }

    @Test
    fun getDetailTvShow() {
        val tvShowLive = MutableLiveData<TvShow>()
        tvShowLive.value = dummyTvShow

        if (dummyTvShowId != null) {
            `when`(movieRepository.getTvShow(dummyTvShowId)).thenReturn(tvShowLive)
            val tvShow = viewModel.getTvShow().value as TvShow
            verify(movieRepository).getTvShow(dummyTvShowId)

            assertNotNull(tvShow)
            assertEquals(dummyTvShow.id, tvShow.id)
            assertEquals(dummyTvShow.originalName, tvShow.originalName)
            assertEquals(dummyTvShow.name, tvShow.name)
            assertEquals(dummyTvShow.overview, tvShow.overview)
            assertEquals(dummyTvShow.backdropPath, tvShow.backdropPath)
            assertEquals(dummyTvShow.posterPath, tvShow.posterPath)
            assertEquals(dummyTvShow.firstAirDate, tvShow.firstAirDate)
            assertEquals(dummyTvShow.voteAverage, tvShow.voteAverage)
            assertEquals(dummyTvShow.voteCount, tvShow.voteCount)
            assertEquals(dummyTvShow.numberOfEpisodes, tvShow.numberOfEpisodes)
            assertEquals(dummyTvShow.numberOfSeasons, tvShow.numberOfSeasons)
            assertEquals(dummyTvShow.genres, tvShow.genres)

            viewModel.getTvShow().observeForever(tvShowObserver)
            Mockito.verify(tvShowObserver).onChanged(dummyTvShow)
        }
    }
}