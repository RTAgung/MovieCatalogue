package com.example.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.data.source.remote.CallbackApiListener
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.*
import com.example.moviecatalogue.utils.Helper.getMovieGenres
import com.example.moviecatalogue.utils.Helper.getTvShowGenres

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
                message?.let { Log.d(TAG, message) }
            }
        })
        return itemResults
    }

    override fun getTrendingTvShow(): LiveData<List<TvShow>> {
        val itemResults = MutableLiveData<List<TvShow>>()
        remoteDataSource.getTrendingTvShow(object : CallbackApiListener<TrendingTvShowResponse> {
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
                message?.let { Log.d(TAG, message) }
            }
        })
        return itemResults
    }

    override fun getMovie(movieId: String): LiveData<Movie> {
        val itemResult = MutableLiveData<Movie>()
        remoteDataSource.getMovie(movieId, object : CallbackApiListener<MovieResponse> {
            override fun onSuccess(response: MovieResponse?) {
                if (response != null) {
                    val movie = Movie(
                        id = response.id,
                        originalTitle = response.originalTitle,
                        title = response.title,
                        overview = response.overview,
                        releaseDate = response.releaseDate,
                        runtime = response.runtime,
                        voteCount = response.voteCount,
                        voteAverage = response.voteAverage,
                        tagline = response.tagline,
                        genres = getMovieGenres(response.genres),
                        backdropPath = response.backdropPath
                    )
                    itemResult.postValue(movie)
                }
            }

            override fun onFailed(message: String?) {
                message?.let { Log.d(TAG, message) }
            }
        })
        return itemResult
    }

    override fun getTvShow(tvShowId: String): LiveData<TvShow> {
        val itemResult = MutableLiveData<TvShow>()
        remoteDataSource.getTvShow(tvShowId, object : CallbackApiListener<TvShowResponse> {
            override fun onSuccess(response: TvShowResponse?) {
                if (response != null) {
                    val tvShow = TvShow(
                        id = response.id,
                        originalName = response.originalName,
                        name = response.name,
                        overview = response.overview,
                        firstAirDate = response.firstAirDate,
                        numberOfEpisodes = response.numberOfEpisodes,
                        numberOfSeasons = response.numberOfSeasons,
                        voteCount = response.voteCount,
                        voteAverage = response.voteAverage,
                        genres = getTvShowGenres(response.genres),
                        backdropPath = response.backdropPath
                    )
                    itemResult.postValue(tvShow)
                }
            }

            override fun onFailed(message: String?) {
                message?.let { Log.d(TAG, message) }
            }
        })
        return itemResult
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