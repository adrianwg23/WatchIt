package com.example.adrianwong.watchit.entities

data class TvShow(
    var id: Int = -1 ,
    var name: String,
    var voteAverage: Double = 0.0,
    var posterPath: String? = null,
    var backDropPath: String? = null,
    var overView: String? = null,
    var firstAirDate: String? = null,
    var isFavourite: Boolean = false
)