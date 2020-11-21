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
        val movieResource = movie.value
        if (movieResource != null) {
            val movie = movieResource.data
            if (movie != null) {
                favorite = FavoriteEntity(
                    id = movie.id,
                    title = movie.title,
                    originalTitle = movie.originalTitle,
                    releaseDate = movie.releaseDate,
                    posterPath = movie.posterPath,
                    type = ConstantValue.TYPE_MOVIE
                )
            }
        }
        return favorite
    }
}