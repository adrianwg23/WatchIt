package com.example.adrianwong.watchit.contentlist.tvshowlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.TvShow

class TvShowListViewModel(val tvShows: MutableLiveData<List<TvShow>> = MutableLiveData()) : ViewModel(), IContentListContract.ViewModel