package com.example.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.*
import com.example.moviecatalogue.utils.Helper.getMovieGenres
import com.example.moviecatalogue.utils.Helper.getTvShowGenres

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    override fun getTopMovies(): LiveData<List<MovieEntity>> {
        val itemResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getTopMovies(object :
            CallbackApiListener<List<TrendingMovieResultsItem>> {
            override fun onSuccess(response: List<TrendingMovieResultsItem>?) {
                val listMovies = ArrayList<MovieEntity>()
                if (response != null) {
                    for (itemResponse in response) {
                        val item = MovieEntity(
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

    override fun getTopTvShows(): LiveData<List<TvShowEntity>> {
        val itemResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTopTvShows(object :
            CallbackApiListener<List<TrendingTvShowResultsItem>> {
            override fun onSuccess(response: List<TrendingTvShowResultsItem>?) {
                val listMovies = ArrayList<TvShowEntity>()
                if (response != null) {
                    for (itemResponse in response) {
                        val item = TvShowEntity(
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

    override fun getMovie(movieId: String): LiveData<MovieEntity> {
        val itemResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovie(movieId, object : CallbackApiListener<MovieResponse> {
            override fun onSuccess(response: MovieResponse?) {
                if (response != null) {
                    val movie = MovieEntity(
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

    override fun getTvShow(tvShowId: String): LiveData<TvShowEntity> {
        val itemResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShow(tvShowId, object : CallbackApiListener<TvShowResponse> {
            override fun onSuccess(response: TvShowResponse?) {
                if (response != null) {
                    val tvShow = TvShowEntity(
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
        val TAG = FakeMovieRepository::class.simpleName
    }
}