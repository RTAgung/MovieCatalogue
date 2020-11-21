package com.example.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.response.*
import com.example.moviecatalogue.data.source.remote.service.MovieApiConfig
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiConfig: MovieApiConfig) {

    fun getTopMovies(): LiveData<ApiResponse<List<TopMovieResultsItem>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TopMovieResultsItem>>>()
        val client = apiConfig.getApiService()?.getTopMovie()
        client?.enqueue(object : Callback<TopMovieResponse> {
            override fun onResponse(
                call: Call<TopMovieResponse>,
                response: Response<TopMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val listMovies = response.body()?.results
                    if (listMovies != null)
                        result.value = ApiResponse.success(listMovies)
                    else
                        result.value = ApiResponse.empty(ERROR_DATA)
                } else result.value = ApiResponse.error(ERROR_RESPONSE)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TopMovieResponse>, t: Throwable) {
                result.value = t.message?.let { ApiResponse.error(it) }
                EspressoIdlingResource.decrement()
            }
        })
        return result
    }

    fun getTopTvShows(): LiveData<ApiResponse<List<TopTvShowResultsItem>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TopTvShowResultsItem>>>()
        val client = apiConfig.getApiService()?.getTopTvShow()
        client?.enqueue(object : Callback<TopTvShowResponse> {
            override fun onResponse(
                call: Call<TopTvShowResponse>,
                response: Response<TopTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    val listTvShows = response.body()?.results
                    if (listTvShows != null)
                        result.value = ApiResponse.success(listTvShows)
                    else
                        result.value = ApiResponse.empty(ERROR_DATA)
                } else result.value = ApiResponse.error(ERROR_RESPONSE)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TopTvShowResponse>, t: Throwable) {
                result.value = t.message?.let { ApiResponse.error(it) }
                EspressoIdlingResource.decrement()
            }
        })
        return result
    }

    fun getMovie(movieId: String): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieResponse>>()
        val client = apiConfig.getApiService()?.getMovie(movieId)
        client?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null)
                        result.value = ApiResponse.success(items)
                    else
                        result.value = ApiResponse.empty(ERROR_DATA)
                } else result.value = ApiResponse.error(ERROR_RESPONSE)

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                result.value = t.message?.let { ApiResponse.error(it) }
                EspressoIdlingResource.decrement()
            }
        })
        return result
    }

    fun getTvShow(tvShowId: String): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShowResponse>>()
        val client = apiConfig.getApiService()?.getTvShow(tvShowId)
        client?.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null)
                        result.value = ApiResponse.success(items)
                    else
                        result.value = ApiResponse.empty(ERROR_DATA)
                } else result.value = ApiResponse.error(ERROR_RESPONSE)

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                result.value = ApiResponse.error(ERROR_RESPONSE)
                EspressoIdlingResource.decrement()
            }
        })
        return result
    }

    companion object {
        const val ERROR_RESPONSE = "Failed Response"
        const val ERROR_DATA = "Null Data"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: MovieApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiConfig)
            }
    }
}