package com.example.adrianwong.data.mappers

import com.example.adrianwong.data.entities.TvShowData
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity

object TvShowDataToTvShowEntityMapper : Mapper<TvShowData, TvShowEntity>() {

    override fun mapFrom(from: TvShowData): TvShowEntity {
        return TvShowEntity(
            id = from.id,
            name = from.name,
            voteAverage = from.voteAverage,
            posterPath = from.posterPath,
            backDropPath = from.backDropPath,
            overView = from.overView,
            firstAirDate = from.firstAirDate
        )
    }
}
