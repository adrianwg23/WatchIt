package com.example.adrianwong.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adrianwong.data.entities.MovieData

const val DATABASE_NAME = "favourites_database"

@Database(entities = [MovieData::class], version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: FavouritesDatabase? = null

        fun getInstance(context: Context): FavouritesDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FavouritesDatabase {
            return Room.databaseBuilder(context, FavouritesDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

}