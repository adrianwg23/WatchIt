package com.example.adrianwong.watchit.entities

abstract class Content(
    open var id: Int = -1,
    open var title: String,
    open var voteAverage: Double = 0.0,
    open var posterUrl: String? = null,
    open var posterPath: String? = null,
    open var backdropUrl: String? = null,
    open var backDropPath: String? = null,
    open var overView: String? = null,
    open var releaseDate: String? = null,
    open var isFavourite: Boolean = false,
    open val contentType: ContentType
)

enum class ContentType(val num: Int) {
    MOVIE(0),
    TV_SHOW(1)
}