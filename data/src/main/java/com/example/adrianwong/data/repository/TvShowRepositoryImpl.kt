package com.example.adrianwong.data.repository

import com.example.adrianwong.data.api.ApiConstants
import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.data.db.FavouritesDatabase
import com.example.adrianwong.data.entities.TvShowData
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.ITvShowRepository

class TvShowRepositoryImpl(private val movieApiService: MovieApiService,
                           private val database: FavouritesDatabase,
                           private val dataToEntityMapper: Mapper<TvShowData, TvShowEntity>,
                           private val entityToDataMapper: Mapper<TvShowEntity, TvShowData>) : ITvShowRepository {

    override suspend fun getPopularTvShows(page: Int): List<TvShowEntity> {
        val tvShowListResult = movieApiService.getPopularTvShows(ApiConstants.API_KEY, ApiConstants.LANGUAGE, page).await()
        return tvShowListResult.tvShows.map { tvData ->
            dataToEntityMapper.mapFrom(tvData)
        }
    }

    override suspend fun getTvShowDetails(): TvShowEntity? {
        return null
    }

    override suspend fun searchTvShow(): List<TvShowEntity>? {
        return null
    }

    override suspend fun getFavouriteTvShows(): List<TvShowEntity> {
        return database.favouritesDao().getFavouriteTvShows()
            .map { dataToEntityMapper.mapFrom(it) }
    }

    override suspend fun saveTvShow(tvShowEntity: TvShowEntity) {
        database.favouritesDao().saveTvShow(entityToDataMapper.mapFrom(tvShowEntity))
    }

    override suspend fun removeTvShow(tvShowEntity: TvShowEntity) {
        database.favouritesDao().removeTvShow(entityToDataMapper.mapFrom(tvShowEntity))
    }

}