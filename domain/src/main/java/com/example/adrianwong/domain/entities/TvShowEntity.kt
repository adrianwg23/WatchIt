package com.example.adrianwong.domain.entities

data class TvShowEntity(
    var id: Int = -1 ,
    var name: String,
    var voteAverage: Double = 0.0,
    var posterPath: String? = null,
    var backDropPath: String? = null,
    var overView: String? = null,
    var firstAirDate: String? = null
)