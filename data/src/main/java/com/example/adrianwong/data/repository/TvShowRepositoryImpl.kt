package com.example.adrianwong.data.repository

import com.example.adrianwong.data.api.ApiConstants
import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.data.db.FavouritesDatabase
import com.example.adrianwong.data.toTvShowData
import com.example.adrianwong.data.toTvShowEntity
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.repository.ITvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(private val movieApiService: MovieApiService,
                                               private val database: FavouritesDatabase) : ITvShowRepository {

    override suspend fun getPopularTvShows(page: Int): List<TvShowEntity> {
        val tvShowListResult = movieApiService.getPopularTvShows(ApiConstants.API_KEY, ApiConstants.LANGUAGE, page).await()
        return tvShowListResult.tvShows.map { it.toTvShowEntity() }
    }

    override suspend fun searchTvShow(): List<TvShowEntity>? {
        return null
    }

    override suspend fun getFavouriteTvShows(): List<TvShowEntity> {
        return database.favouritesDao().getFavouriteTvShows().map { it.toTvShowEntity() }
    }

    override suspend fun saveTvShow(tvShowEntity: TvShowEntity) {
        database.favouritesDao().saveTvShow(tvShowEntity.toTvShowData())
    }

    override suspend fun removeTvShow(tvShowEntity: TvShowEntity) {
        database.favouritesDao().removeTvShow(tvShowEntity.toTvShowData())
    }

    override suspend fun checkFavouriteTvShow(tvShowId: Int): Boolean {
        return database.favouritesDao().getTvShow(tvShowId) != null
    }
}