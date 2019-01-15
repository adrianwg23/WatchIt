package com.example.adrianwong.watchit.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow

class RemovedFavouritesViewModel(
    override val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData(),
    override val tvShows: MutableLiveData<MutableList<TvShow>> = MutableLiveData()
) : ViewModel(), IFavouritesContract.ViewModel