package com.example.moviecatalogue.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.utils.ConstantValue.TYPE_MOVIE
import com.example.moviecatalogue.utils.ConstantValue.TYPE_TV_SHOW
import com.example.moviecatalogue.vo.Resource

class DetailActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val id = MutableLiveData<String>()
    private val type = MutableLiveData<String>()

    fun setId(id: String) {
        this.id.value = id
    }

    fun setType(type: String) {
        this.type.value = type
    }

    var movie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(id) { mMovieId ->
            movieRepository.getMovie(mMovieId)
        }

    var tvShow: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(id) { mTvShowId ->
            movieRepository.getTvShow(mTvShowId)
        }

    fun checkFavorite(): LiveData<Int>? = id.value?.let { movieRepository.checkFavorite(it) }

    fun insertFavorite() {
        val favorite = getFavorite()
        if (favorite != null)
            movieRepository.insertFavorite(favorite)
    }

    fun deleteFavorite() {
        val favorite = getFavorite()
        if (favorite != null)
            movieRepository.deleteFavorite(favorite)
    }

    private fun getFavorite(): FavoriteEntity? {
        var favorite: FavoriteEntity? = null
        if (type.value == TYPE_TV_SHOW) {
            val tvShowResource = tvShow.value
            if (tvShowResource != null) {
                val tvShow = tvShowResource.data
                if (tvShow != null) {
                    favorite = FavoriteEntity(
                        id = tvShow.id,
                        title = tvShow.name,
                        originalTitle = tvShow.originalName,
                        releaseDate = tvShow.firstAirDate,
                        posterPath = tvShow.posterPath,
                        type = TYPE_TV_SHOW
                    )
                }
            }
        } else {
            val movieResource = movie.value
            Log.d("ERROR 1", movieResource.toString())
            if (movieResource != null) {
                val movie = movieResource.data
                Log.d("ERROR 2", movie.toString())
                if (movie != null) {
                    favorite = FavoriteEntity(
                        id = movie.id,
                        title = movie.title,
                        originalTitle = movie.originalTitle,
                        releaseDate = movie.releaseDate,
                        posterPath = movie.posterPath,
                        type = TYPE_MOVIE
                    )
                    Log.d("ERROR 3", favorite.toString())
                }
            }
        }
        return favorite
    }
}