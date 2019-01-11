package com.example.adrianwong.domain.repository

interface ITvShowRepository {

    fun getPopularTvShows()
    fun getTvShowDetails()
    fun searchTvShow()
}