package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository

class RemoveFavouriteContent(private val movieRepository: IMovieRepository,
                             private val tvShowRepository: ITvShowRepository) {

    suspend fun removeMovie(movieEntity: MovieEntity) {
        movieRepository.removeMovie(movieEntity)
    }

    suspend fun removeTvShow(tvShowEntity: TvShowEntity) {
        tvShowRepository.removeTvShow(tvShowEntity)
    }
}