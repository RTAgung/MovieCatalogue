package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.utils.ConstantValue
import com.example.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val movieId = MutableLiveData<String>()

    fun setMovieId(movieId: String) {
        this.movieId.value = movieId
    }

    var movie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { mMovieId ->
            movieRepository.getMovie(mMovieId)
        }
}