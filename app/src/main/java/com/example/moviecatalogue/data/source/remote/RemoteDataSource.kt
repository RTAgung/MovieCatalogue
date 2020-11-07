package com.example.moviecatalogue.data.source.remote

import com.example.moviecatalogue.data.source.remote.response.*
import com.example.moviecatalogue.data.source.remote.service.MovieApiConfig
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiConfig: MovieApiConfig) {

    fun getTrendingMovie(callback: CallbackApiListener<TrendingMovieResponse>) {
        EspressoIdlingResource.increment()
        val client = apiConfig.getApiService()?.getTrendingMovie()
        client?.enqueue(object : Callback<TrendingMovieResponse> {
            override fun onResponse(
                call: Call<TrendingMovieResponse>,
                response: Response<TrendingMovieResponse>
            ) {
                val items = response.body()
                if (items != null) callback.onSuccess(items)
                else callback.onFailed("Items Null")
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TrendingMovieResponse>, t: Throwable) {
                callback.onFailed(t.message)
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTrendingTvShow(callback: CallbackApiListener<TrendingTvShowResponse>) {
        EspressoIdlingResource.increment()
        val client = apiConfig.getApiService()?.getTrendingTvShow()
        client?.enqueue(object : Callback<TrendingTvShowResponse> {
            override fun onResponse(
                call: Call<TrendingTvShowResponse>,
                response: Response<TrendingTvShowResponse>
            ) {
                val items = response.body()
                if (items != null) callback.onSuccess(items)
                else callback.onFailed("Items Null")
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TrendingTvShowResponse>, t: Throwable) {
                callback.onFailed(t.message)
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getMovie(movieId: String, callback: CallbackApiListener<MovieResponse>) {
        EspressoIdlingResource.increment()
        val client = apiConfig.getApiService()?.getMovie(movieId)
        client?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val items = response.body()
                if (items != null) callback.onSuccess(items)
                else callback.onFailed("Items Null")
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onFailed(t.message)
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShow(tvShowId: String, callback: CallbackApiListener<TvShowResponse>) {
        EspressoIdlingResource.increment()
        val client = apiConfig.getApiService()?.getTvShow(tvShowId)
        client?.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                val items = response.body()
                if (items != null) callback.onSuccess(items)
                else callback.onFailed("Items Null")
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                callback.onFailed(t.message)
                EspressoIdlingResource.decrement()
            }
        })
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: MovieApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiConfig)
            }
    }
}