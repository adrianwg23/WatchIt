package com.example.adrianwong.watchit.contentlist.tvshowlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.watchit.common.notifyObserver
import com.example.adrianwong.watchit.common.toTvShow
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowListLogic(dispatcher: DispatcherProvider,
                      view: IContentListContract.View,
                      viewModel: IContentListContract.ViewModel,
                      private val getPopularTvShows: GetPopularTvShows) : ContentListLogic(dispatcher, view, viewModel) {

    override fun event(event: ContentListEvent) {
        when(event) {
            is ContentListEvent.OnLoadMoreData -> onLoadMoreData()
            else -> super.event(event)
        }
    }

    override fun onStart() {
        jobTracker = Job()

        if (mViewModel.tvShows.value.isNullOrEmpty()) {
            updateTvShowList(mViewModel.tvShowsPageNumber++)
            mView.showLoadingView()
        } else {
            mView.hideLoadingView()
        }
    }

    private fun onLoadMoreData() {
        updateTvShowList(mViewModel.tvShowsPageNumber++)
    }

    private fun updateTvShowList(page: Int) = launch {
        try {
            val tvShows = withContext(dispatcher.provideIOContext()) {
                getPopularTvShows.execute(page).map { it.toTvShow() }
            }

            mViewModel.tvShows.value?.run {
                addAll(tvShows)
                mViewModel.tvShows.notifyObserver()
            } ?: run {
                mViewModel.tvShows.value = tvShows.toMutableList()
            }
        } catch (e: Exception) {
            mView.showError(e.localizedMessage)
        } finally {
            mView.hideLoadingView()
        }
    }
}