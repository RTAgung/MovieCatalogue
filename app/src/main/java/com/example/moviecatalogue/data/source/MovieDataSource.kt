package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.vo.Resource

interface MovieDataSource {
    fun getTopMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovie(movieId: String): LiveData<Resource<MovieEntity>>

    fun getTopTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTvShow(tvShowId: String): LiveData<Resource<TvShowEntity>>

    fun getFavorites(): LiveData<PagedList<FavoriteEntity>>

    fun insertFavorite(favorite: FavoriteEntity)

    fun deleteFavorite(favorite: FavoriteEntity)
}