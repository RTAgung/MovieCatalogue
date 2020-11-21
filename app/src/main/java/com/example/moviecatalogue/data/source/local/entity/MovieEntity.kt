package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "originalTitle") var originalTitle: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "tagline") var tagline: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "releaseDate") var releaseDate: String? = null,
    @ColumnInfo(name = "voteAverage") var voteAverage: Double? = null,
    @ColumnInfo(name = "voteCount") var voteCount: Int? = null,
    @ColumnInfo(name = "runtime") var runtime: Int? = null,
    @ColumnInfo(name = "posterPath") var posterPath: String? = null,
    @ColumnInfo(name = "backdropPath") var backdropPath: String? = null,
    @ColumnInfo(name = "genres") var genres: String? = null
)