package com.example.adrianwong.domain.repository

interface IMovieRepository {

    fun getPopularMovies()
    fun getMovieDetails()
    fun searchMovie()
}