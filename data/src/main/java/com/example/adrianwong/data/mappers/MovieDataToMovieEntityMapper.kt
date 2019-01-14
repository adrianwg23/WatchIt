package com.example.adrianwong.data.mappers

import com.example.adrianwong.data.entities.MovieData
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity

object MovieDataToMovieEntityMapper : Mapper<MovieData, MovieEntity>() {

    override fun mapFrom(from: MovieData): MovieEntity {
        return MovieEntity(
            id = from.id,
            title = from.title,
            voteAverage = from.voteAverage,
            posterPath = from.posterPath,
            overView = from.overView,
            releaseDate = from.releaseDate
        )
    }

}
