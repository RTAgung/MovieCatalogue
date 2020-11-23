package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.NetworkBoundResource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.*
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.Helper.getMovieGenres
import com.example.moviecatalogue.utils.Helper.getTvShowGenres
import com.example.moviecatalogue.vo.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {

    override fun getTopMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<TopMovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTopMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TopMovieResultsItem>>> =
                remoteDataSource.getTopMovies()

            override fun saveCallResult(data: List<TopMovieResultsItem>) {
                val movies = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        originalTitle = response.originalTitle,
                        title = response.title,
                        releaseDate = response.releaseDate,
                        voteAverage = response.voteAverage,
                        posterPath = response.posterPath
                    )
                    movies.add(movie)
                }

                localDataSource.insertMovies(movies)
            }
        }.asLiveData()
    }

    override fun getTopTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TopTvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTopTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TopTvShowResultsItem>>> =
                remoteDataSource.getTopTvShows()

            override fun saveCallResult(data: List<TopTvShowResultsItem>) {
                val tvShows = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        id = response.id,
                        originalName = response.originalName,
                        name = response.name,
                        firstAirDate = response.firstAirDate,
                        voteAverage = response.voteAverage,
                        posterPath = response.posterPath
                    )
                    tvShows.add(tvShow)
                }

                localDataSource.insertTvShows(tvShows)
            }
        }.asLiveData()
    }

    override fun getMovie(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.genres == null || data.runtime == null

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovie(movieId)

            override fun saveCallResult(data: MovieResponse) {
                val movie = MovieEntity(
                    id = data.id,
                    originalTitle = data.originalTitle,
                    title = data.title,
                    overview = data.overview,
                    releaseDate = data.releaseDate,
                    runtime = data.runtime,
                    voteCount = data.voteCount,
                    voteAverage = data.voteAverage,
                    tagline = data.tagline,
                    genres = getMovieGenres(data.genres),
                    backdropPath = data.backdropPath,
                    posterPath = data.posterPath
                )

                localDataSource.setDetailMovie(movie)
            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data?.genres == null || data.numberOfEpisodes == null

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShow(tvShowId)

            override fun saveCallResult(data: TvShowResponse) {
                val tvShow = TvShowEntity(
                    id = data.id,
                    originalName = data.originalName,
                    name = data.name,
                    overview = data.overview,
                    firstAirDate = data.firstAirDate,
                    numberOfEpisodes = data.numberOfEpisodes,
                    numberOfSeasons = data.numberOfSeasons,
                    voteCount = data.voteCount,
                    voteAverage = data.voteAverage,
                    genres = getTvShowGenres(data.genres),
                    backdropPath = data.backdropPath,
                    posterPath = data.posterPath
                )

                localDataSource.setDetailTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavorites(): LiveData<PagedList<FavoriteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavorites(), config).build()
    }

    override fun checkFavorite(favoriteId: String): LiveData<Int> =
        localDataSource.checkFavorite(favoriteId)

    override fun insertFavorite(favorite: FavoriteEntity) =
        appExecutors.diskIO().execute { localDataSource.insertFavorite(favorite) }

    override fun deleteFavorite(favorite: FavoriteEntity) =
        appExecutors.diskIO().execute { localDataSource.deleteFavorite(favorite) }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = MovieRepository(remoteData, localData, appExecutors)
                    }
                }
            }
            return instance as MovieRepository
        }
    }
}