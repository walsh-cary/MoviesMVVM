package com.example.moviesmvvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DaoMovies::class],version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getDao(): DaoMovies

    companion object{
        @Volatile
        private var movieInstance : MoviesDatabase? = null

        fun getInstance(context: Context) : MoviesDatabase{
            val tempInstance = movieInstance
            if (tempInstance != null)
                return tempInstance
            synchronized(this){
                val instanceRoom = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "movies_db"
                ).build()
                movieInstance = instanceRoom
                return instanceRoom
            }
        }
    }
}