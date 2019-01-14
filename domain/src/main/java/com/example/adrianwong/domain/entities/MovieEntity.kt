package com.example.adrianwong.domain.entities


data class MovieEntity(
    var id: Int = -1 ,
    var title: String,
    var voteAverage: Double = 0.0,
    var posterPath: String? = null,
    var backDropPath: String? = null,
    var overView: String? = null,
    var releaseDate: String? = null
)