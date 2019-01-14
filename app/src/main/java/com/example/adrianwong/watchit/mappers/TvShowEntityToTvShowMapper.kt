package com.example.adrianwong.watchit.mappers

import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.watchit.entities.TvShow

object TvShowEntityToTvShowMapper : Mapper<TvShowEntity, TvShow>() {

    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

    override fun mapFrom(from: TvShowEntity): TvShow {
        return TvShow(
            id = from.id,
            name = from.name,
            voteAverage = from.voteAverage,
            posterPath = from.posterPath?.let { POSTER_BASE_URL + from.posterPath },
            overView = from.overView,
            firstAirDate = from.firstAirDate
        )
    }

}