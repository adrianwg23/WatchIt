package com.example.adrianwong.domain.repository

import com.example.adrianwong.domain.entities.MovieEntity

interface IMovieRepository {

    suspend fun getPopularMovies(page: Int): List<MovieEntity>
    suspend fun getFavouriteMovies(): List<MovieEntity>
    suspend fun saveMovie(movieEntity: MovieEntity)
    suspend fun removeMovie(movieEntity: MovieEntity)
    suspend fun getMovieDetails(): MovieEntity?
    suspend fun searchMovie(): List<MovieEntity>?
}