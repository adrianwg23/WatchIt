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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowListLogic(dispatcher: DispatcherProvider,
                      view: IContentListContract.View,
                      viewModel: IContentListContract.ViewModel<TvShow>,
                      private val mapper: Mapper<TvShowEntity, TvShow>,
                      private val getPopularTvShows: GetPopularTvShows,
                      private val searchTvShow: SearchTvShow) : ContentListLogic<TvShow>(dispatcher, view, viewModel) {

    override fun onListItemClick(content: TvShow) {
    }

    override fun onItemFavourited(position: Int) {
        val tvShow = mViewModel.content.value!![position]
        tvShow.isFavourite = !tvShow.isFavourite
    }

    override fun onListRefresh() {
        updateTvShowList(1)
        mViewModel.pageNumber = 1
    }

    override fun onLoadMoreData() {
        updateTvShowList(mViewModel.pageNumber++)
    }

    override fun onStart() {
        if (mViewModel.content.value.isNullOrEmpty()) {
            updateTvShowList(mViewModel.pageNumber++)
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

        mViewModel.content.value?.run {
            addAll(tvShows)
            mViewModel.content.notifyObserver()
        } ?: run {
            mViewModel.content.value = tvShows.toMutableList()
        }
    }
}