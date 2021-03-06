package com.example.adrianwong.watchit.contentlist.movielist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.watchit.common.notifyObserver
import com.example.adrianwong.watchit.common.toMovie
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.dagger.movies.MoviesScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@MoviesScope
class MovieListLogic @Inject constructor(view: IContentListContract.View,
                                         viewModel: IContentListContract.ViewModel,
                                         private val getPopularMovies: GetPopularMovies) :
    ContentListLogic(DispatcherProvider, view, viewModel) {

    override fun event(event: ContentListEvent) {
        when(event) {
            is ContentListEvent.OnLoadMoreData -> onLoadMoreData()
            else -> super.event(event)
        }
    }

    override fun onStart() {
        jobTracker = Job()

        if (mViewModel.movies.value.isNullOrEmpty()) {
            updateMovieList(mViewModel.moviesPageNumber++)
            mView.showLoadingView()
        } else {
            mView.hideLoadingView()
        }
    }

    private fun onLoadMoreData() {
        updateMovieList(mViewModel.moviesPageNumber++)
    }

    private fun updateMovieList(page: Int) = launch {
        try {
            val movies = withContext(dispatcher.provideIOContext()) {
                getPopularMovies.execute(page).map { it.toMovie() }
            }

            mViewModel.movies.value?.run {
                // this block is run when we want to ADD more movies to the list (ie. we fetch page 2)
                addAll(movies)
                mViewModel.movies.notifyObserver()
            } ?: run {
                mViewModel.movies.value = movies.toMutableList()
            }
        } catch (e: Exception) {
        mView.showError(e.localizedMessage)
    } finally {
        mView.hideLoadingView()
        }
    }
}