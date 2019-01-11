package com.example.adrianwong.watchit.contentlist.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieListVMFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel() as T
    }

}