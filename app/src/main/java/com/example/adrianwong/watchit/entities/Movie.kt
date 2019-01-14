package com.example.adrianwong.watchit.entities

data class Movie(
    var id: Int = -1 ,
    var title: String,
    var voteAverage: Double = 0.0,
    var posterPath: String? = null,
    var overView: String? = null,
    var releaseDate: String? = null,
    var isFavourite: Boolean = false
)