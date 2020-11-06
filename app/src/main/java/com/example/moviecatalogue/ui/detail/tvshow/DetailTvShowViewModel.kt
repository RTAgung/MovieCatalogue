package com.example.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.utils.DataDummy

class DetailTvShowViewModel : ViewModel() {
    private lateinit var tvShowId: String
    private var tvShow: TvShow? = null

    fun setTvShowId(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun setTvShow() {
        val listTvShows = DataDummy.generateDummyTvShow()
        for (tvShow in listTvShows) {
            if (tvShow.id == tvShowId)
                this.tvShow = tvShow
        }
    }

    fun getTvShow(): TvShow? {
        if (tvShow == null)
            setTvShow()
        return tvShow
    }
}