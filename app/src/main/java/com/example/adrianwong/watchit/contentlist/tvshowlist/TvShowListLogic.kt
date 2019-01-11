package com.example.adrianwong.watchit.contentlist.tvshowlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchTvShow
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract

class TvShowListLogic(dispatcher: DispatcherProvider,
                      view: IContentListContract.View,
                      viewModel: IContentListContract.TvShowListViewModel,
                      private val getPopularTvShows: GetPopularTvShows,
                      private val searchTvShow: SearchTvShow) : ContentListLogic(dispatcher, view) {


    override fun onListItemClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}