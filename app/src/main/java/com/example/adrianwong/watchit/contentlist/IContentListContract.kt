package com.example.adrianwong.watchit.contentlist

import com.example.adrianwong.domain.domainmodel.Movie

interface IContentListContract {

    interface View {
        fun setAdapater()
        fun showList()
        fun showLoadingView()
        fun setToolBarTitle()
        fun startContentDetailsActivity()
    }

    interface ViewModel {
        fun setMovieList()
        fun getMovieList()
        fun setTvShowList()
        fun getTvShowList()
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