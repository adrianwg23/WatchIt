package com.example.adrianwong.watchit.contentlist

import com.example.adrianwong.watchit.contentlist.movielist.MovieListLogic
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListLogic

class ContentListLogic(private val movieListLogic: MovieListLogic, private val tvShowListLogic: TvShowListLogic) {

    fun hello() = movieListLogic.test()
}