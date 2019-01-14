package com.example.adrianwong.watchit.contentlist.tvshowlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.TvShow

class TvShowListViewModel(override val content: MutableLiveData<MutableList<TvShow>> = MutableLiveData(),
                          override var pageNumber: Int = 1) : ViewModel(),
    IContentListContract.ViewModel<TvShow>