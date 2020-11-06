package com.example.moviecatalogue.data.source.remote.service

import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TrendingMovieResponse
import com.example.moviecatalogue.data.source.remote.response.TrendingTvShowResponse
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import com.example.moviecatalogue.utils.ConstantValue.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("/trending/movie/week?api_key=$API_KEY")
    fun getTrendingMovie(): Call<TrendingMovieResponse>

    @GET("/trending/tv/week?api_key=$API_KEY")
    fun getTrendingTvShow(): Call<TrendingTvShowResponse>

    @GET("/movie/{movie_id}?api_key=$API_KEY")
    fun getMovie(@Path("movie_id") movieId: Int): Call<MovieResponse>

    @GET("/tv/{tv_id}?api_key=$API_KEY")
    fun getTvShow(@Path("tv_id") tvShowId: Int): Call<TvShowResponse>
}