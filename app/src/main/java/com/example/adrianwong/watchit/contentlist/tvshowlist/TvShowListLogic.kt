package com.example.adrianwong.watchit.contentlist.tvshowlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.TvShowEntity
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchTvShow
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
                      private val mapper: Mapper<TvShowEntity, TvShow>,
                      private val getPopularTvShows: GetPopularTvShows,
                      private val searchTvShow: SearchTvShow) : ContentListLogic<TvShow>(dispatcher, view, viewModel) {

    override fun onListItemClick(content: TvShow) {
    }

    override fun onItemFavourited(position: Int) {
        val tvShow = mViewModel.tvShows.value!![position]
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
                mapper.mapFrom(tvShowEntity)
            }
        }

        mViewModel.tvShows.value?.run {
            addAll(tvShows)
            mViewModel.tvShows.notifyObserver()
        } ?: run {
            mViewModel.tvShows.value = tvShows.toMutableList()
        }
    }
}