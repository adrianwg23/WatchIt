package com.example.adrianwong.watchit.contentlist.tvshowlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchTvShow
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract

class TvShowListLogic(dispatcher: DispatcherProvider,
                      private val getPopularTvShows: GetPopularTvShows,
                      private val searchTvShow: SearchTvShow) : ContentListLogic(dispatcher) {

    override fun onListItemClick() {
    }

    override fun onListRefresh() {
    }

    override fun onStart() {
    }

    override fun onBind(view: IContentListContract.View, viewModel: IContentListContract.ViewModel) {
        this.view = view
        this.viewModel = viewModel
    }

}