package com.example.adrianwong.watchit.favourites

import androidx.lifecycle.MutableLiveData
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow

interface IFavouritesContract {

    interface View {
        fun setAdapter()
        fun showLoadingView()
        fun setToolBarTitle()
    }

    interface ViewModel {
        val movies: MutableLiveData<MutableList<Movie>>
        val tvShows: MutableLiveData<MutableList<TvShow>>
    }
}