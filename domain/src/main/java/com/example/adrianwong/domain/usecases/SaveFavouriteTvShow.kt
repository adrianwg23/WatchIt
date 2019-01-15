package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.ITvShowRepository

class SaveFavouriteTvShow(private val tvShowRepository: ITvShowRepository) {

    suspend fun execute(tvShowEntity: TvShowEntity) {
        tvShowRepository.saveTvShow(tvShowEntity)
    }
}