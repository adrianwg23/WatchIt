package com.example.adrianwong.data.api

import com.example.adrianwong.data.entities.MovieData
import com.google.gson.annotations.SerializedName

data class MovieListResult(
    @SerializedName("results")
    var movies: List<MovieData>
)