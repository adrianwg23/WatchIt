package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.ITvShowRepository

class RemoveFavouriteTvShow(private val tvShowRepository: ITvShowRepository) {

    suspend fun execute(tvShowEntity: TvShowEntity) {
        tvShowRepository.removeTvShow(tvShowEntity)
    }
}