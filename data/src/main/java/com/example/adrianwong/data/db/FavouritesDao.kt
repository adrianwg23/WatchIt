package com.example.adrianwong.data.db

import androidx.room.*
import com.example.adrianwong.data.entities.MovieData
import com.example.adrianwong.data.entities.TvShowData

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM movies")
    fun getFavouriteMovies(): List<MovieData>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    fun getMovie(movieId: Int): MovieData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieData)

    @Delete
    fun removeMovie(movie: MovieData)

    @Query("SELECT * FROM tvshows")
    fun getFavouriteTvShows(): List<TvShowData>

    @Query("SELECT * FROM tvshows WHERE id=:tvShowId")
    fun getTvShow(tvShowId: Int): TvShowData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTvShow(tvShow: TvShowData)

    @Delete
    fun removeTvShow(tvShow: TvShowData)

}