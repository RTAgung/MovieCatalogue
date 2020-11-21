package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.MovieDatabase
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.service.MovieApiConfig
import com.example.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(MovieApiConfig())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}