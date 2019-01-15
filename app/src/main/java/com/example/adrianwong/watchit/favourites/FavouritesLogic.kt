package com.example.adrianwong.watchit.favourites

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.usecases.GetFavouriteMovies
import com.example.adrianwong.domain.usecases.GetFavouriteTvShows
import com.example.adrianwong.watchit.common.BaseLogic
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.ContentType
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FavouritesLogic(dispatcher: DispatcherProvider,
                      private val entityToMovieMapper: Mapper<MovieEntity, Movie>,
                      private val entityToTvShowMapper: Mapper<TvShowEntity, TvShow>,
                      private val getFavouriteMovies: GetFavouriteMovies,
                      private val getFavouriteTvShows: GetFavouriteTvShows,
                      private val view: IFavouritesContract.View,
                      private val favouritesViewModel: IFavouritesContract.ViewModel,
                      private val removedFavouritesViewModel: IFavouritesContract.ViewModel):
    BaseLogic(dispatcher), IContentListContract.Logic, CoroutineScope {

    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    private var contentType = ContentType.MOVIE


    override fun event(event: ContentListEvent) {
        when(event) {
            is ContentListEvent.OnStart -> onStart()
            is ContentListEvent.OnBind -> onBind()
            is ContentListEvent.OnItemFavourited -> onItemFavourite(event.position)
            is ContentListEvent.OnFavouriteContentChanged -> onFavouriteContentChanged(event.contentType)
            is ContentListEvent.OnDestroy -> onDestroy()
        }
    }

    private fun onStart() {
        jobTracker = Job()
        getFavourites(contentType)
    }

    private fun onBind() {
        view.run {
            setAdapter()
            setToolBarTitle()
        }
    }

    private fun onDestroy() {
        jobTracker.cancel()
    }

    private fun onItemFavourite(position: Int) {

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
        val favouriteMovies = withContext(dispatcher.provideIOContext()) {
            getFavouriteMovies.execute().map {
                entityToMovieMapper.mapFrom(it)
            }.map { it.copy(isFavourite = true) }
        }

        favouritesViewModel.movies.value = favouriteMovies.toMutableList()
    }

    private fun getFavouriteTvShows() = launch {
        val favouriteTvShows = withContext(dispatcher.provideIOContext()) {
            getFavouriteTvShows.execute().map {
                entityToTvShowMapper.mapFrom(it)
            }.map { it.copy(isFavourite = true) }
        }

        favouritesViewModel.tvShows.value = favouriteTvShows.toMutableList()
    }
}