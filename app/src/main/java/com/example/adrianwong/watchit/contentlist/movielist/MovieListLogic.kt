package com.example.adrianwong.watchit.contentlist.movielist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import kotlinx.coroutines.Job

class MovieListLogic<T>(dispatcher: DispatcherProvider,
                     private val getPopularMovies: GetPopularMovies,
                     private val searchMovie: SearchMovie) : ContentListLogic<T>(dispatcher) {

    override fun onListItemClick() {
    }

    override fun onListRefresh() {
    }

    override fun onStart() {
        jobTracker = Job()
        if (mViewModel?.content?.value.isNullOrEmpty()) {
            getPopularMovies.execute()
        }
    }

    override fun onBind(view: IContentListContract.View, viewModel: IContentListContract.ViewModel<T>) {
        mView = view
        mViewModel = viewModel

        mView?.run {
            setToolBarTitle()
            setAdapter()
        }
    }
}