package com.example.moviecatalogue.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/*
Scenario DetailTvShowViewModelTest :
    - Memuat Detail Tv Show
        - Memanipulasi data ketika pemanggilan data detail tv show di kelas repository
        - Memastikan metode di kelas repository terpanggil
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
    private lateinit var tvShowEntityObserver: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(movieRepository)
        dummyTvShowId?.let { viewModel.setTvShowId(it) }
    }

    @Test
    fun getDetailTvShow() {
        val tvShowLive = MutableLiveData<TvShowEntity>()
        tvShowLive.value = dummyTvShow

        if (dummyTvShowId != null) {
            `when`(movieRepository.getTvShow(dummyTvShowId)).thenReturn(tvShowLive)
            val tvShow = viewModel.getTvShow().value as TvShowEntity
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

            viewModel.getTvShow().observeForever(tvShowEntityObserver)
            Mockito.verify(tvShowEntityObserver).onChanged(dummyTvShow)
        }
    }
}