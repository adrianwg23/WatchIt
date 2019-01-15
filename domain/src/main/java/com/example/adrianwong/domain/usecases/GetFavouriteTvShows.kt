package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.ITvShowRepository

class GetFavouriteTvShows(private val tvShowRepository: ITvShowRepository) {

    suspend fun execute(): List<TvShowEntity> {
        return tvShowRepository.getFavouriteTvShows()
    }
}