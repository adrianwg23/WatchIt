package com.example.adrianwong.data.repository

import com.example.adrianwong.data.api.ApiConstants
import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.data.db.FavouritesDatabase
import com.example.adrianwong.data.toMovieData
import com.example.adrianwong.data.toMovieEntity
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApiService: MovieApiService,
                                              private val database: FavouritesDatabase) : IMovieRepository {

    override suspend fun getPopularMovies(page: Int): List<MovieEntity> {
        val movieListResult = movieApiService.getPopularMovies(ApiConstants.API_KEY, ApiConstants.LANGUAGE, page).await()
        return movieListResult.movies.map { it.toMovieEntity() }
    }

    override suspend fun searchMovie(): List<MovieEntity>? {
        return null
    }

    override suspend fun getFavouriteMovies(): List<MovieEntity> {
        return database.favouritesDao().getFavouriteMovies().map { it.toMovieEntity() }
    }

    override suspend fun saveMovie(movieEntity: MovieEntity) {
        database.favouritesDao().saveMovie(movieEntity.toMovieData())
    }

    override suspend fun removeMovie(movieEntity: MovieEntity) {
        database.favouritesDao().removeMovie(movieEntity.toMovieData())
    }

    override suspend fun checkFavouriteMovie(movieId: Int): Boolean {
        return database.favouritesDao().getMovie(movieId) != null
    }
}