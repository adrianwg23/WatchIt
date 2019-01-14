package com.example.adrianwong.data.repository

import com.example.adrianwong.data.api.ApiConstants
import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.data.entities.TvShowData
import com.example.adrianwong.data.mappers.TvShowDataToTvShowEntityMapper
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.ITvShowRepository

class TvShowRepositoryImpl(private val movieApiService: MovieApiService,
                           private val mapper: Mapper<TvShowData, TvShowEntity>) : ITvShowRepository {


    override suspend fun getPopularTvShows(page: Int): List<TvShowEntity> {
        val tvShowListResult = movieApiService.getPopularTvShows(ApiConstants.API_KEY, ApiConstants.LANGUAGE, page).await()
        return tvShowListResult.tvShows.map { tvData ->
            mapper.mapFrom(tvData)
        }
    }

    override suspend fun getTvShowDetails(): TvShowEntity? {
        return null
    }

    override suspend fun searchTvShow(): List<TvShowEntity>? {
        return null
    }

}