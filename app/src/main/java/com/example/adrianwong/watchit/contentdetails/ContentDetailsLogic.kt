package com.example.adrianwong.watchit.contentdetails

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.CheckFavouriteStatus
import com.example.adrianwong.domain.usecases.RemoveFavouriteContent
import com.example.adrianwong.domain.usecases.SaveFavouriteContent
import com.example.adrianwong.watchit.common.BaseLogic
import com.example.adrianwong.watchit.common.toMovieEntity
import com.example.adrianwong.watchit.common.toTvShowEntity
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Contains logic for ContentDetailsActivity
 */
class ContentDetailsLogic(dispatcher: DispatcherProvider,
                          private val mView: IContentDetailsContract.View,
                          private val mViewModel: IContentDetailsContract.ViewModel,
                          private val checkFavouriteStatus: CheckFavouriteStatus,
                          private val saveFavouriteContent: SaveFavouriteContent,
                          private val removeFavouriteContent: RemoveFavouriteContent) :
    BaseLogic(dispatcher), IContentDetailsContract.Logic, CoroutineScope {

    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    override fun event(event: ContentDetailsEvent) {
        when(event) {
            is ContentDetailsEvent.OnItemFavourited -> onItemFavourited(event.content)
            is ContentDetailsEvent.OnStart -> onStart(event.content)
            is ContentDetailsEvent.OnBind -> onBind()
            is ContentDetailsEvent.OnDestroy -> onDestroy()
        }
    }

    /**
     * Because we can't return booleans from coroutine 'launch' blocks, we have to launch each coroutine
     * individually when checking if it is favourite
     */
    private fun onItemFavourited(content: Content) {
        when(content) {
            is Movie -> {
                launch {
                    try {
                        val isFavourite = withContext(dispatcher.provideIOContext()) {
                            checkFavouriteStatus.checkFavouriteMovie(content.id)
                        }
                        if (isFavourite) removeMovie(content) else saveMovie(content)
                        updateState(content, !isFavourite)
                    } catch (e: Exception) {
                        mView.showError(e.localizedMessage)
                    }
                }
            }
            is TvShow -> {
                launch {
                    try {
                        val isFavourite = withContext(dispatcher.provideIOContext()) {
                            checkFavouriteStatus.checkFavouriteTvShow(content.id)
                        }
                        if(isFavourite) removeTvShow(content) else saveTvShow(content)
                        updateState(content, !isFavourite)
                    } catch (e: Exception) {
                        mView.showError(e.localizedMessage)
                    }
                }
            }
        }
    }

    private fun onStart(content: Content) {
        if (mViewModel.favouriteState.value == null) {
            when(content) {
                is Movie -> {
                    launch {
                        try {
                            val isFavourite = withContext(dispatcher.provideIOContext()) {
                                checkFavouriteStatus.checkFavouriteMovie(content.id)
                            }
                            updateState(content, isFavourite)
                        } catch (e: Exception) {
                            mView.showError(e.localizedMessage)
                        }
                    }
                }
                is TvShow -> {
                    launch {
                        try {
                            val isFavourite = withContext(dispatcher.provideIOContext()) {
                                checkFavouriteStatus.checkFavouriteTvShow(content.id)
                            }
                            updateState(content, isFavourite)
                        } catch (e: Exception) {
                            mView.showError(e.localizedMessage)
                        }
                    }
                }
            }
        }
    }

    private fun onBind() {
        mView.setupUi()
    }

    private fun onDestroy() {
        jobTracker.cancel()
    }

    private fun saveMovie(movie: Movie) = launch {
        try {
            withContext(dispatcher.provideIOContext()) {
                saveFavouriteContent.saveMovie(movie.toMovieEntity())
            }
        } catch (e: Exception) {
            mView.showError(e.localizedMessage)
        }
    }

    private fun removeMovie(movie: Movie) = launch {
        try {
            withContext(dispatcher.provideIOContext()) {
                removeFavouriteContent.removeMovie(movie.toMovieEntity())
            }
        } catch (e: Exception) {
            mView.showError(e.localizedMessage)
        }
    }

    private fun saveTvShow(tvShow: TvShow) = launch {
        try {
            withContext(dispatcher.provideIOContext()) {
                saveFavouriteContent.saveTvShow(tvShow.toTvShowEntity())
            }
        } catch (e: Exception) {
            mView.showError(e.localizedMessage)
        }
    }

    private fun removeTvShow(tvShow: TvShow) = launch {
        try {
            withContext(dispatcher.provideIOContext()) {
                removeFavouriteContent.removeTvShow(tvShow.toTvShowEntity())
            }
        } catch (e: Exception) {
            mView.showError(e.localizedMessage)
        }
    }

    private fun updateState(content: Content, isFavourite: Boolean) {
        content.isFavourite = isFavourite
        mViewModel.favouriteState.value = isFavourite
    }
}