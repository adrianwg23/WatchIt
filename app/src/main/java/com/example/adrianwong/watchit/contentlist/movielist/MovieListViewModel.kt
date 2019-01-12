package com.example.adrianwong.watchit.contentlist.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie

class MovieListViewModel : ViewModel(), IContentListContract.MovieListViewModel {

    val movies = MutableLiveData<List<Movie>>()

    override fun setMovieList(newMovieList: List<Movie>) {
        movies.value = newMovieList
    }

    override fun getMovieList(): List<Movie>? {
        return movies.value
    }
}
