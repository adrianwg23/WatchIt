package com.example.adrianwong.watchit.mappers

import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.watchit.entities.Movie

object MovieToMovieEntityMapper : Mapper<Movie, MovieEntity>() {

    override fun mapFrom(from: Movie): MovieEntity {
        return MovieEntity(
            id = from.id,
            title = from.title,
            voteAverage = from.voteAverage,
            posterPath = from.posterUrl,
            backDropPath = from.backdropUrl,
            overView = from.overView,
            releaseDate = from.releaseDate
        )
    }

}