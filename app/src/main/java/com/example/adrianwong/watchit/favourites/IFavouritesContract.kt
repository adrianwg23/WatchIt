package com.example.adrianwong.watchit.favourites

import androidx.lifecycle.MutableLiveData
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow

interface IFavouritesContract {

    interface View {
        fun setAdapter()
        fun showLoadingView()
        fun hideLoadingView()
        fun showError(error: String)
        fun setToolBarTitle()
        fun startContentDetailsActivity(content: Content, view: android.view.View)
    }

    interface ViewModel {
        val movies: MutableLiveData<MutableList<Movie>>
        val tvShows: MutableLiveData<MutableList<TvShow>>
    }
}