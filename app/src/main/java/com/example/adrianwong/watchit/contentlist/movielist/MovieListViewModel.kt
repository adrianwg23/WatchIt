package com.example.adrianwong.watchit.contentlist.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie

class MovieListViewModel(override val content: MutableLiveData<List<Movie>> = MutableLiveData(),
                         override var pageNumber: Int = 1) : ViewModel(),
    IContentListContract.ViewModel<Movie>
