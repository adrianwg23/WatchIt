package com.example.adrianwong.watchit.favourites

import android.view.View
import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetFavouriteMovies
import com.example.adrianwong.domain.usecases.GetFavouriteTvShows
import com.example.adrianwong.watchit.common.BaseLogic
import com.example.adrianwong.watchit.common.toMovie
import com.example.adrianwong.watchit.common.toTvShow
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.ContentType
import com.example.adrianwong.watchit.entities.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class FavouritesLogic(dispatcher: DispatcherProvider,
                      private val getFavouriteMovies: GetFavouriteMovies,
                      private val getFavouriteTvShows: GetFavouriteTvShows,
                      private val mView: IFavouritesContract.View,
                      private val mViewModel: IFavouritesContract.ViewModel):
    BaseLogic(dispatcher), IContentListContract.Logic, CoroutineScope {

    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    private var contentType = ContentType.MOVIE


    override fun event(event: ContentListEvent) {
        when(event) {
            is ContentListEvent.OnListItemClick -> onListItemClick(event.content, event.view)
            is ContentListEvent.OnStart -> onStart()
            is ContentListEvent.OnBind -> onBind()
            is ContentListEvent.OnFavouriteContentChanged -> onFavouriteContentChanged(event.contentType)
            is ContentListEvent.OnDestroy -> onDestroy()
        }
    }

    private fun onListItemClick(content: Content, view: View) {
        mView.startContentDetailsActivity(content, view)
    }

    private fun onStart() {
        jobTracker = Job()
        getFavourites(contentType)
        mView.showLoadingView()
    }

    private fun onBind() {
        mView.run {
            setAdapter()
            setToolBarTitle()
        }
    }

    private fun onDestroy() {
        jobTracker.cancel()
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