package com.example.adrianwong.data.mappers

import com.example.adrianwong.data.entities.MovieData
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity

object MovieEntityToMovieDataMapper : Mapper<MovieEntity, MovieData>() {

    override fun mapFrom(from: MovieEntity): MovieData {
        return MovieData(
            id = from.id,
            title = from.title,
            voteAverage = from.voteAverage,
            posterPath = from.posterPath,
            backDropPath = from.backDropPath,
            overView = from.overView,
            releaseDate = from.releaseDate
        )
    }

}