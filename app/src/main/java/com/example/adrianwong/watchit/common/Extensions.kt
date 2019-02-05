package com.example.adrianwong.watchit.common

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow
import com.example.adrianwong.watchit.mappers.MovieEntityToMovieMapper
import com.example.adrianwong.watchit.mappers.MovieToMovieEntityMapper
import com.example.adrianwong.watchit.mappers.TvShowEntityToTvShowMapper
import com.example.adrianwong.watchit.mappers.TvShowToTvShowEntityMapper

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun ImageView.load(url: String, context: Fragment) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.load(url: String, context: Activity) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun Activity.makeToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

// mapping extensions
fun Movie.toMovieEntity() = MovieToMovieEntityMapper.mapFrom(this)

fun TvShow.toTvShowEntity() = TvShowToTvShowEntityMapper.mapFrom(this)

fun MovieEntity.toMovie() = MovieEntityToMovieMapper.mapFrom(this)

fun TvShowEntity.toTvShow() = TvShowEntityToTvShowMapper.mapFrom(this)