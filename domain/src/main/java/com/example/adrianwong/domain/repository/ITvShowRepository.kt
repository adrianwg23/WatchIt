package com.example.adrianwong.domain.repository

import com.example.adrianwong.domain.entities.TvShowEntity

interface ITvShowRepository {

    suspend fun getPopularTvShows(page: Int): List<TvShowEntity>
    suspend fun getTvShowDetails(): TvShowEntity?
    suspend fun searchTvShow(): List<TvShowEntity>?
}