package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.repository.IMovieRepository

class GetFavouriteMovies(private val movieRepository: IMovieRepository) {

    suspend fun execute(): List<MovieEntity> {
        return movieRepository.getFavouriteMovies()
    }
}