package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "originalTitle") var originalTitle: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "releaseDate") var releaseDate: String? = null,
    @ColumnInfo(name = "posterPath") var posterPath: String? = null,
    @ColumnInfo(name = "type") var type: String? = null
)