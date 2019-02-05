package com.example.adrianwong.watchit.contentlist.favourites

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetFavouriteMovies
import com.example.adrianwong.domain.usecases.GetFavouriteTvShows
import com.example.adrianwong.watchit.common.toMovie
import com.example.adrianwong.watchit.common.toTvShow
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.ContentType
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesLogic(dispatcher: DispatcherProvider,
                      view: IContentListContract.View,
                      viewModel: IContentListContract.ViewModel,
                      private val getFavouriteMovies: GetFavouriteMovies,
                      private val getFavouriteTvShows: GetFavouriteTvShows): ContentListLogic(dispatcher, view, viewModel){

    private var contentType = ContentType.MOVIE

    override fun event(event: ContentListEvent) {
        when (event) {
            is ContentListEvent.OnFavouriteContentChanged -> onFavouriteContentChanged(event.contentType)
            else -> super.event(event)
        }
    }

    override fun onStart() {
        jobTracker = Job()
        getFavourites(contentType)
        mView.showLoadingView()
    }

    private fun onFavouriteContentChanged(contentType: ContentType) {
        if (this.contentType != contentType) {
            getFavourites(contentType)
            this.contentType = contentType
        }
    }

    private fun getFavourites(contentType: ContentType) {
        when (contentType) {
            ContentType.MOVIE -> getFavouriteMovies()
            ContentType.TV_SHOW -> getFavouriteTvShows()
        }
    }

    private fun getFavouriteMovies() = launch {
        try {
            val favouriteMovies = withContext(dispatcher.provideIOContext()) {
                getFavouriteMovies.execute().map { it.toMovie() }
            }

            mViewModel.movies.value = favouriteMovies.toMutableList()
        } catch (e: Exception) {
            mView.showError(e.localizedMessage)
        } finally {
            mView.hideLoadingView()
        }
    }

    private fun getFavouriteTvShows() = launch {
        try {
            val favouriteTvShows = withContext(dispatcher.provideIOContext()) {
                getFavouriteTvShows.execute().map { it.toTvShow() }
            }

            mViewModel.tvShows.value = favouriteTvShows.toMutableList()
        } catch (e: Exception) {
            mView.showError(e.localizedMessage)
        } finally {
            mView.hideLoadingView()
        }
    }
}