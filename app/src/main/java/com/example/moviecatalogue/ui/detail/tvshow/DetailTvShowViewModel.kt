package com.example.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.utils.ConstantValue.TYPE_TV_SHOW
import com.example.moviecatalogue.vo.Resource

class DetailTvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val tvShowId = MutableLiveData<String>()

    fun setTvShowId(tvShowId: String) {
        this.tvShowId.value = tvShowId
    }

    var tvShow: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            movieRepository.getTvShow(mTvShowId)
        }
}