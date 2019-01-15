package com.example.adrianwong.watchit.mappers

import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.watchit.entities.TvShow

object TvShowEntityToTvShowMapper : Mapper<TvShowEntity, TvShow>() {

    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
    private const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780"

    override fun mapFrom(from: TvShowEntity): TvShow {
        return TvShow(
            id = from.id,
            title = from.name,
            voteAverage = from.voteAverage,
            posterUrl = from.posterPath,
            posterPath = from.posterPath?.let { POSTER_BASE_URL + from.posterPath },
            backdropUrl = from.backDropPath,
            backDropPath = from.backDropPath?.let { BACKDROP_BASE_URL + from.backDropPath },
            overView = from.overView,
            releaseDate = from.firstAirDate
        )
    }

}