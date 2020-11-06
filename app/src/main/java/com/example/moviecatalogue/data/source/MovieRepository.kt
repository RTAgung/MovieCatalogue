package com.example.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.data.source.remote.CallbackApiListener
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TrendingMovieResponse
import com.example.moviecatalogue.data.source.remote.response.TrendingTvShowResponse

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    override fun getTrendingMovie(): LiveData<List<Movie>> {
        val itemResults = MutableLiveData<List<Movie>>()
        remoteDataSource.getTrendingMovie(object : CallbackApiListener<TrendingMovieResponse> {
            override fun onSuccess(response: TrendingMovieResponse?) {
                val listMovies = ArrayList<Movie>()
                val responseResults = response?.results
                if (responseResults != null) {
                    for (itemResponse in responseResults) {
                        val item = Movie(
                            id = itemResponse.id,
                            originalTitle = itemResponse.originalTitle,
                            title = itemResponse.title,
                            releaseDate = itemResponse.releaseDate,
                            voteAverage = itemResponse.voteAverage,
                            posterPath = itemResponse.posterPath
                        )

                        listMovies.add(item)
                    }
                } else Log.d(TAG, "getTrendingMovie() null")
                itemResults.postValue(listMovies)
            }

            override fun onFailed(message: String?) {
                Log.d(TAG, message)
            }
        })
        return itemResults
    }

    override fun getTrendingTvShow(): LiveData<List<TvShow>> {
        val itemResults = MutableLiveData<List<TvShow>>()
        remoteDataSource.getTrendingTvShow(object : CallbackApiListener<TrendingTvShowResponse>{
            override fun onSuccess(response: TrendingTvShowResponse?) {
                val listMovies = ArrayList<TvShow>()
                val responseResults = response?.results
                if (responseResults != null) {
                    for (itemResponse in responseResults) {
                        val item = TvShow(
                            id = itemResponse.id,
                            originalName = itemResponse.originalName,
                            name = itemResponse.name,
                            firstAirDate = itemResponse.firstAirDate,
                            voteAverage = itemResponse.voteAverage,
                            posterPath = itemResponse.posterPath
                        )

                        listMovies.add(item)
                    }
                } else Log.d(TAG, "getTrendingMovie() null")
                itemResults.postValue(listMovies)
            }

            override fun onFailed(message: String?) {
                Log.d(TAG, message)
            }
        })
        return itemResults
    }

    override fun getMovie(movieId: Int): LiveData<Movie> {
        val itemResult = MutableLiveData<Movie>()
        remoteDataSource.getMovie(object : CallbackApiListener<MovieResponse>{
            override fun onSuccess(response: MovieResponse?) {
                TODO("Not yet implemented")
            }
// TODO: LENGKAPIN
            override fun onFailed(message: String?) {
                Log.d(TAG, message)
            }
        })
        return itemResult
    }

    override fun getTvShow(tvShowId: Int): LiveData<TvShow> {
        TODO("Not yet implemented")
    }

    companion object {
        val TAG = MovieRepository::class.simpleName

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData)
            }
    }
}