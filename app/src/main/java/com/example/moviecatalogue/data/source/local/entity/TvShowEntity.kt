package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "originalName") var originalName: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "firstAirDate") var firstAirDate: String? = null,
    @ColumnInfo(name = "numberOfEpisodes") var numberOfEpisodes: Int? = null,
    @ColumnInfo(name = "numberOfSeasons") var numberOfSeasons: Int? = null,
    @ColumnInfo(name = "voteAverage") var voteAverage: Double? = null,
    @ColumnInfo(name = "voteCount") var voteCount: Int? = null,
    @ColumnInfo(name = "backdropPath") var backdropPath: String? = null,
    @ColumnInfo(name = "posterPath") var posterPath: String? = null,
    @ColumnInfo(name = "genres") var genres: String? = null
)