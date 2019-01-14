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
                          private val mapper: Mapper<MovieData, MovieEntity>) : IMovieRepository {


    override suspend fun getPopularMovies(page: Int): List<MovieEntity> {
        val movieListResult = movieApiService.getPopularMovies(ApiConstants.API_KEY, ApiConstants.LANGUAGE, page).await()
        return movieListResult.movies.map { movieData ->
            mapper.mapFrom(movieData)
        }
    }

    override suspend fun getMovieDetails(): MovieEntity? {
        return null
    }

    override suspend fun searchMovie(): List<MovieEntity>? {
        return null
    }

}