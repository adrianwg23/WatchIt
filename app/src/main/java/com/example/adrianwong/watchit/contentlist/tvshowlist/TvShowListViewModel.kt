package com.example.adrianwong.watchit.contentlist.tvshowlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.TvShow

class TvShowListViewModel : ViewModel(), IContentListContract.TvShowListViewModel {

    val tvShows = MutableLiveData<List<TvShow>>()

    override fun setTvShowList(newTvShowList: List<TvShow>) {
        tvShows.value = newTvShowList
    }

    override fun getTvShowList(): List<TvShow>? {
        return tvShows.value
    }
}
