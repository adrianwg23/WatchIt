package com.example.adrianwong.watchit.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow

class FavouritesViewModel(
    override val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData(),
    override val tvShows: MutableLiveData<MutableList<TvShow>> = MutableLiveData(),
    override var moviesPageNumber: Int = -1,
    override var tvShowsPageNumber: Int = -1) : ViewModel(), IContentListContract.ViewModel