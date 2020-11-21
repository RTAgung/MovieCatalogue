package com.example.moviecatalogue.data.source.remote.service

import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TopMovieResponse
import com.example.moviecatalogue.data.source.remote.response.TopTvShowResponse
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import com.example.moviecatalogue.utils.ConstantValue.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("/3/movie/top_rated?page=1&api_key=$API_KEY")
    fun getTopMovie(): Call<TopMovieResponse>

    @GET("/3/tv/top_rated?page=1&api_key=$API_KEY")
    fun getTopTvShow(): Call<TopTvShowResponse>

    @GET("/3/movie/{movie_id}?api_key=$API_KEY")
    fun getMovie(@Path("movie_id") movieId: String): Call<MovieResponse>

    @GET("/3/tv/{tv_id}?api_key=$API_KEY")
    fun getTvShow(@Path("tv_id") tvShowId: String): Call<TvShowResponse>
}