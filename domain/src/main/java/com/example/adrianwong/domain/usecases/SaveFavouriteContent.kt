package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository

class SaveFavouriteContent(private val movieRepository: IMovieRepository,
                           private val tvShowRepository: ITvShowRepository) {

    suspend fun saveMovie(movieEntity: MovieEntity) {
        movieRepository.saveMovie(movieEntity)
    }

    suspend fun saveTvShow(tvShowEntity: TvShowEntity) {
        tvShowRepository.saveTvShow(tvShowEntity)
    }
}