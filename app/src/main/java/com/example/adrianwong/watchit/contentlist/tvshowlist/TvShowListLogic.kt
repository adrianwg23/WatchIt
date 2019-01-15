package com.example.adrianwong.watchit.contentlist.tvshowlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.RemoveFavouriteTvShow
import com.example.adrianwong.domain.usecases.SaveFavouriteTvShow
import com.example.adrianwong.watchit.common.notifyObserver
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.TvShow
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowListLogic(dispatcher: DispatcherProvider,
                      view: IContentListContract.View,
                      viewModel: IContentListContract.ViewModel,
                      private val entityToTvShowMapper: Mapper<TvShowEntity, TvShow>,
                      private val tvShowToEntityMapper: Mapper<TvShow, TvShowEntity>,
                      private val getPopularTvShows: GetPopularTvShows,
                      private val saveFavouriteTvShow: SaveFavouriteTvShow,
                      private val removeFavouriteTvShow: RemoveFavouriteTvShow) : ContentListLogic<TvShow>(dispatcher, view, viewModel) {

    override fun onListItemClick(content: TvShow) {
    }

    override fun onItemFavourited(position: Int) {
        val tvShow = mViewModel.tvShows.value!![position]

        if(!tvShow.isFavourite) {
            saveTvShow(tvShow)
        } else {
            removeTvShow(tvShow)
        }

        tvShow.isFavourite = !tvShow.isFavourite
    }

    override fun onListRefresh() {
        updateTvShowList(1)
        mViewModel.tvShowsPageNumber = 1
    }

    override fun onLoadMoreData() {
        updateTvShowList(mViewModel.tvShowsPageNumber++)
    }

    override fun onStart() {
        jobTracker = Job()

        if (mViewModel.tvShows.value.isNullOrEmpty()) {
            updateTvShowList(mViewModel.tvShowsPageNumber++)
        }
    }

    override fun onBind() {
        mView.run {
            setToolBarTitle()
            setAdapter()
        }
    }

    private fun updateTvShowList(page: Int) = launch {
        val tvShows = withContext(dispatcher.provideIOContext()) {
            getPopularTvShows.execute(page).map {tvShowEntity ->
                entityToTvShowMapper.mapFrom(tvShowEntity)
            }
        }

        mViewModel.tvShows.value?.run {
            addAll(tvShows)
            mViewModel.tvShows.notifyObserver()
        } ?: run {
            mViewModel.tvShows.value = tvShows.toMutableList()
        }
    }

    private fun saveTvShow(tvShow: TvShow) = launch {
        withContext(dispatcher.provideIOContext()) {
            saveFavouriteTvShow.execute(tvShowToEntityMapper.mapFrom(tvShow))
        }
    }

    private fun removeTvShow(tvShow: TvShow) = launch {
        withContext(dispatcher.provideIOContext()) {
            removeFavouriteTvShow.execute(tvShowToEntityMapper.mapFrom(tvShow))
        }
    }
}