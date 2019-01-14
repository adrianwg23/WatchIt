package com.example.adrianwong.data.entities

import com.google.gson.annotations.SerializedName

data class MovieData(

    @SerializedName("id")
    var id: Int = -1 ,

    @SerializedName("title")
    var title: String,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("overview")
    var overView: String? = null,

    @SerializedName("release_data")
    var releaseDate: String? = null
)

