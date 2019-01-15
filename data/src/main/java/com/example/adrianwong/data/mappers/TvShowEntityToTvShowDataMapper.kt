package com.example.adrianwong.data.mappers

import com.example.adrianwong.data.entities.TvShowData
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity

object TvShowEntityToTvShowDataMapper : Mapper<TvShowEntity, TvShowData>() {

    override fun mapFrom(from: TvShowEntity): TvShowData {
        return TvShowData(
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
