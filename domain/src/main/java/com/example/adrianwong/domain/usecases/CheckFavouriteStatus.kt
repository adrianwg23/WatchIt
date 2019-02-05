package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository

class CheckFavouriteStatus(private val movieRepository: IMovieRepository,
                           private val tvShowRepository: ITvShowRepository) {

    suspend fun checkFavouriteMovie(movieId: Int): Boolean {
        return movieRepository.checkFavouriteMovie(movieId)
    }

    suspend fun checkFavouriteTvShow(tvShowId: Int): Boolean {
        return tvShowRepository.checkFavouriteTvShow(tvShowId)
    }
}