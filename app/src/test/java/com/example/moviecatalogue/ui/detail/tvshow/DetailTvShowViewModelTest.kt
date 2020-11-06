package com.example.moviecatalogue.ui.detail.tvshow

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/*
Scenario DetailTvShowViewModelTest :
    - Memuat Detail Tv Show
        - Memastikan data tv show tidak null
        - Memastikan data tv show sesuai dengan yang diharapkan
 */

class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val dummyTvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        dummyTvShowId?.let { viewModel.setTvShowId(it) }
    }

    @Test
    fun getDetailTvShow() {
        dummyTvShow.id?.let { viewModel.setTvShowId(it) }
        val tvShow = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow?.id)
        assertEquals(dummyTvShow.originalName, tvShow?.originalName)
        assertEquals(dummyTvShow.name, tvShow?.name)
        assertEquals(dummyTvShow.overview, tvShow?.overview)
        assertEquals(dummyTvShow.backdropPath, tvShow?.backdropPath)
        assertEquals(dummyTvShow.posterPath, tvShow?.posterPath)
        assertEquals(dummyTvShow.firstAirDate, tvShow?.firstAirDate)
        assertEquals(dummyTvShow.voteAverage, tvShow?.voteAverage)
    }
}