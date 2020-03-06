package com.example.moviesmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
class MoviesEntity(
    @ColumnInfo(name="movie_title")
    val title: String,
    val year: String,
    val poster: String,
    val genre: String
) {

}