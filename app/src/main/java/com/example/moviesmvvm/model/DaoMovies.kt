package com.example.moviesmvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoMovies {
    @Insert
    fun saveDataIntoCache(data: List<MoviesEntity>)

    @Query("SELECT * FROM movies_table")
    fun getDataFromCache(): List<MoviesEntity>
}