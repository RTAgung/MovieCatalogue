package com.example.moviecatalogue.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Room
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.room.MovieDao
import com.example.moviecatalogue.data.source.local.room.MovieDatabase

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    fun getTopMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getTopMovies()

    fun getMovieById(movieId: String): LiveData<MovieEntity> = movieDao.getMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun setDetailMovie(movie: MovieEntity) = movieDao.updateMovie(movie)

    fun refreshMovie() = movieDao.deleteMovies()

    fun getTopTvShows(): DataSource.Factory<Int, TvShowEntity> = movieDao.getTopTvShows()

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> = movieDao.getTvShowById(tvShowId)

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)

    fun setDetailTvShow(tvShow: TvShowEntity) = movieDao.updateTvShow(tvShow)

    fun refreshTvShow() = movieDao.deleteTvShows()

    fun getFavorites(): DataSource.Factory<Int, FavoriteEntity> = movieDao.getFavorites()

    fun checkFavorite(favoriteId: String): LiveData<Int> = movieDao.checkFavorite(favoriteId)

    fun insertFavorite(favorite: FavoriteEntity) = movieDao.insertFavorite(favorite)

    fun deleteFavorite(favorite: FavoriteEntity) = movieDao.deleteFavorite(favorite)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(movieDao)
            }
            return INSTANCE as LocalDataSource
        }
    }
}