package com.example.adrianwong.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieData(

    @PrimaryKey
    @SerializedName("id")
    var id: Int = -1 ,

    @SerializedName("title")
    var title: String,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backDropPath: String? = null,

    @SerializedName("overview")
    var overView: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null
)

