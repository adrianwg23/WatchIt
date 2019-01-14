package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.repository.IMovieRepository

class GetPopularMovies(private val movieRepository: IMovieRepository) {

    suspend fun execute(page: Int): List<MovieEntity> {
        return movieRepository.getPopularMovies(page)
    }
}