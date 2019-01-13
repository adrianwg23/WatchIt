package com.example.adrianwong.watchit.contentlist.tvshowlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchTvShow
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TvShowListLogic<T>(dispatcher: DispatcherProvider,
                         view: IContentListContract.View,
                         viewModel: IContentListContract.ViewModel<T>,
                         private val getPopularTvShows: GetPopularTvShows,
                         private val searchTvShow: SearchTvShow) : ContentListLogic<T>(dispatcher, view, viewModel) {

    override fun onListItemClick() {
    }

    override fun onListRefresh() {
    }

    override fun onStart() {
        jobTracker = Job()
        if (mViewModel.content.value.isNullOrEmpty()) {
            updateTvShowList()
        }
    }

    override fun onBind() {
        mView.run {
            setToolBarTitle()
            setAdapter()
        }
    }

    private fun updateTvShowList() = launch {
        getPopularTvShows.execute()
    }
}