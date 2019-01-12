package com.example.adrianwong.watchit.contentlist.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie

class MovieListViewModel(val movies: MutableLiveData<List<Movie>> = MutableLiveData()) : ViewModel(), IContentListContract.ViewModel
