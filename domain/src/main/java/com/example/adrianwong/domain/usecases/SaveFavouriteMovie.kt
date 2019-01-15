package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.repository.IMovieRepository

class SaveFavouriteMovie(private val movieRepository: IMovieRepository) {

    suspend fun execute(movieEntity: MovieEntity) {
        movieRepository.saveMovie(movieEntity)
    }
}