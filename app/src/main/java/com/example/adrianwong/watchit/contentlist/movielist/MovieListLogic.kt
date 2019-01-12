package com.example.adrianwong.watchit.contentlist.movielist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract

class MovieListLogic(dispatcher: DispatcherProvider,
                     private val getPopularMovies: GetPopularMovies,
                     private val searchMovie: SearchMovie) : ContentListLogic(dispatcher) {

    override fun onListItemClick() {
    }

    override fun onListRefresh() {
    }

    override fun onStart() {
    }

    override fun onBind(view: IContentListContract.View, viewModel: IContentListContract.ViewModel) {
        this.view = view
        this.viewModel = viewModel
    }
}