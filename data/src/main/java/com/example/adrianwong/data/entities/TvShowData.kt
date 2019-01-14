package com.example.adrianwong.data.entities

import com.google.gson.annotations.SerializedName

data class TvShowData(

    @SerializedName("id")
    var id: Int = -1 ,

    @SerializedName("name")
    var name: String,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backDropPath: String? = null,

    @SerializedName("overview")
    var overView: String? = null,

    @SerializedName( "first_air_date")
    var firstAirDate: String? = null
)
