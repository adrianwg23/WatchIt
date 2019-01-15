package com.example.adrianwong.watchit.mappers

import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.watchit.entities.TvShow

object TvShowToTvShowEntityMapper : Mapper<TvShow, TvShowEntity>() {

    override fun mapFrom(from: TvShow): TvShowEntity {
        return TvShowEntity(
            id = from.id,
            name = from.title,
            voteAverage = from.voteAverage,
            posterPath = from.posterUrl,
            backDropPath = from.backdropUrl,
            overView = from.overView,
            firstAirDate = from.releaseDate
        )
    }

}