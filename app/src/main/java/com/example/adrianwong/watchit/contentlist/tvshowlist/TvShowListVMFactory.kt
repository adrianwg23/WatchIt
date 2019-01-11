package com.example.adrianwong.watchit.contentlist.tvshowlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TvShowListVMFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowListViewModel() as T
    }
}