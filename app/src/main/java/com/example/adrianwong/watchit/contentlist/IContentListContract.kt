package com.example.adrianwong.watchit.contentlist

interface IContentListContract {

    interface View {
        fun setAdapater()
        fun showList()
        fun showLoadingView()
        fun setToolBarTitle()
        fun startContentDetailsActivity()
    }

    interface MovieListViewModel {
        fun setMovieList()
        fun getMovieList()
    }

    interface TvShowListViewModel {
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