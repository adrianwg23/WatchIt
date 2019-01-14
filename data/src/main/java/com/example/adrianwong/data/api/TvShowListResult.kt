package com.example.adrianwong.data.api

import com.example.adrianwong.data.entities.TvShowData
import com.google.gson.annotations.SerializedName

data class TvShowListResult (
    @SerializedName("results")
    var tvShows: List<TvShowData>
)