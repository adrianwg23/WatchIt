package com.example.adrianwong.watchit.mappers

import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.watchit.entities.Movie

object MovieEntityToMovieMapper : Mapper<MovieEntity, Movie>() {

    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

    override fun mapFrom(from: MovieEntity): Movie {
        return Movie(
            id = from.id,
            title = from.title,
            voteAverage = from.voteAverage,
            posterPath = from.posterPath?.let { POSTER_BASE_URL + from.posterPath },
            overView = from.overView,
            releaseDate = from.releaseDate
        )
    }

}