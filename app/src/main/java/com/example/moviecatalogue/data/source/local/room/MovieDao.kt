package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getTopMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovieById(movieId: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("DELETE FROM movie")
    fun deleteMovies()

    @Query("SELECT * FROM tv_show")
    fun getTopTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show WHERE id = :tvShowId")
    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: TvShowEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("DELETE FROM tv_show")
    fun deleteTvShows()

    @Query("SELECT * FROM favorite")
    fun getFavorites(): DataSource.Factory<Int, FavoriteEntity>

    @Query("SELECT 1 FROM favorite WHERE id = :favoriteId")
    fun checkFavorite(favoriteId: String): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    fun deleteFavorite(favorite: FavoriteEntity)
}