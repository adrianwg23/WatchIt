package com.example.adrianwong.watchit.contentlist

import androidx.lifecycle.MutableLiveData
import com.example.adrianwong.watchit.entities.ContentType
import com.example.adrianwong.watchit.entities.Movie
import com.example.adrianwong.watchit.entities.TvShow

interface IContentListContract {

    interface View {
        fun setAdapter()
        fun showLoadingView()
        fun setToolBarTitle()
        fun startContentDetailsActivity()
    }

    interface ViewModel {
        val movies: MutableLiveData<MutableList<Movie>>
        val tvShows: MutableLiveData<MutableList<TvShow>>
        var moviesPageNumber: Int
        var tvShowsPageNumber: Int
    }

    interface Logic {
        fun event(event: ContentListEvent)
    }
}

sealed class ContentListEvent {
    data class OnListItemClick<out T>(val content: T) : ContentListEvent()
    object OnListRefresh : ContentListEvent()
    object OnLoadMoreData : ContentListEvent()
    data class OnFavouriteContentChanged(val contentType: ContentType): ContentListEvent()
    data class OnItemFavourited(val position: Int) : ContentListEvent()
    object OnStart : ContentListEvent()
    object OnBind : ContentListEvent()
    object OnDestroy : ContentListEvent()
}