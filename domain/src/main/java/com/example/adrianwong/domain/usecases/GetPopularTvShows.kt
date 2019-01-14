package com.example.adrianwong.domain.usecases

import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.ITvShowRepository

class GetPopularTvShows(private val tvShowRepository: ITvShowRepository) {

    suspend fun execute(page: Int): List<TvShowEntity> {
        return tvShowRepository.getPopularTvShows(page)
    }
}