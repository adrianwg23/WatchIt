package com.example.adrianwong.data.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("page") page: Int): Deferred<MovieListResult>

    @GET("tv/popular")
    fun getPopularTvShows(@Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("page") page: Int): Deferred<TvShowListResult>

}

class ApiConstants {
    companion object {
        const val API_KEY = "ba64c71a42b6ccaaed1b66c822d35694"
        const val LANGUAGE = "en-US"
    }
}