package com.example.moviecatalogue.di

import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.service.MovieApiConfig

object Injection {
    fun provideRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance(MovieApiConfig())
        return MovieRepository.getInstance(remoteDataSource)
    }
}