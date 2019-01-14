package com.example.adrianwong.data.db

import androidx.room.*
import com.example.adrianwong.data.entities.MovieData

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM movies")
    fun getFavorites(): List<MovieData>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    fun get(movieId: Int): MovieData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieData)

    @Delete
    fun removeMovie(movie: MovieData)

}