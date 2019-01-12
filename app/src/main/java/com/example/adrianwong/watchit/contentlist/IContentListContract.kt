package com.example.adrianwong.watchit.contentlist

import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow

interface IContentListContract {

    interface View {
        fun setAdapater()
        fun showList()
        fun showLoadingView()
        fun setToolBarTitle()
        fun startContentDetailsActivity()
    }

    interface MovieListViewModel {
        fun setMovieList(newMovieList: List<Movie>)
        fun getMovieList(): List<Movie>?
    }

    interface TvShowListViewModel {
        fun setTvShowList(newTvShowList: List<TvShow>)
        fun getTvShowList(): List<TvShow>?
    }

    interface Logic {
        fun event(event: ContentListEvent)
    }
}

sealed class ContentListEvent {
    data class OnListItemClick<out T>(val content: T) : ContentListEvent()
    object OnListRefresh : ContentListEvent()
    object OnStart : ContentListEvent()
    object OnBind : ContentListEvent()
    object OnDestroy : ContentListEvent()
}