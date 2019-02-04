package com.example.adrianwong.watchit.entities

import java.io.Serializable

data class TvShow(
    override var id: Int = -1,
    override var title: String,
    override var voteAverage: Double = 0.0,
    override var posterUrl: String? = null,
    override var posterPath: String? = null,
    override var backdropUrl: String? = null,
    override var backDropPath: String? = null,
    override var overView: String? = null,
    override var releaseDate: String? = null,
    override var isFavourite: Boolean = false
) : Content(id, title, voteAverage, posterUrl, posterPath, backdropUrl,
    backDropPath, overView, releaseDate, isFavourite, ContentType.TV_SHOW), Serializable