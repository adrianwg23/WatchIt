package com.example.adrianwong.data.repository

import com.example.adrianwong.data.api.ApiConstants
import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.data.db.FavouritesDatabase
import com.example.adrianwong.data.entities.MovieData
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.repository.IMovieRepository

class MovieRepositoryImpl(private val movieApiService: MovieApiService,
                          private val database: FavouritesDatabase,
                          private val dataToEntityMapper: Mapper<MovieData, MovieEntity>,
                          private val entityToDataMapper: Mapper<MovieEntity, MovieData>) : IMovieRepository {

    override suspend fun getPopularMovies(page: Int): List<MovieEntity> {
        val movieListResult = movieApiService.getPopularMovies(ApiConstants.API_KEY, ApiConstants.LANGUAGE, page).await()
        return movieListResult.movies.map { movieData ->
            dataToEntityMapper.mapFrom(movieData)
        }
    }

    override suspend fun searchMovie(): List<MovieEntity>? {
        return null
    }

    override suspend fun getFavouriteMovies(): List<MovieEntity> {
        return database.favouritesDao().getFavouriteMovies()
            .map { dataToEntityMapper.mapFrom(it) }
    }

    override suspend fun saveMovie(movieEntity: MovieEntity) {
        database.favouritesDao().saveMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override suspend fun removeMovie(movieEntity: MovieEntity) {
        database.favouritesDao().removeMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override suspend fun checkFavouriteMovie(movieId: Int): Boolean {
        return database.favouritesDao().getMovie(movieId) != null
    }
}