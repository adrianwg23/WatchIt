package com.example.adrianwong.watchit.entities

data class TvShow(
    override var id: Int = -1,
    override var title: String,
    override var voteAverage: Double = 0.0,
    override var posterPath: String? = null,
    override var backDropPath: String? = null,
    override var overView: String? = null,
    override var releaseDate: String? = null,
    override var isFavourite: Boolean = false
) : Content(id, title, voteAverage, posterPath, backDropPath, overView, releaseDate, isFavourite, ContentType.TV_SHOW)