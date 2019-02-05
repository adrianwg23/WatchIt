package com.example.adrianwong.watchit.contentlist.tvshowlist

import android.view.View
import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.watchit.common.notifyObserver
import com.example.adrianwong.watchit.common.toTvShow
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.TvShow
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowListLogic(dispatcher: DispatcherProvider,
                      view: IContentListContract.View,
                      viewModel: IContentListContract.ViewModel,
                      private val getPopularTvShows: GetPopularTvShows) : ContentListLogic<TvShow>(dispatcher, view, viewModel) {

    override fun onListItemClick(content: Content, view: View) {
        mView.startContentDetailsActivity(content, view)
    }

    override fun onListRefresh() {
    }

    override fun onLoadMoreData() {
        updateTvShowList(mViewModel.tvShowsPageNumber++)
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

    override fun onBind() {
        mView.run {
            setToolBarTitle()
            setAdapter()
        }
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