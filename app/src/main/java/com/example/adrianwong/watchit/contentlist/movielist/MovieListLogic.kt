package com.example.adrianwong.watchit.contentlist.movielist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract

class MovieListLogic(dispatcher: DispatcherProvider,
                     view: IContentListContract.View,
                     viewModel: IContentListContract.MovieListViewModel,
                     private val getPopularMovies: GetPopularMovies,
                     private val searchMovie: SearchMovie) : ContentListLogic(dispatcher, view) {


    override fun onListItemClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}