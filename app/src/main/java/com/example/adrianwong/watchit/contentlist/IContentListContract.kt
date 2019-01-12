package com.example.adrianwong.watchit.contentlist

interface IContentListContract {

    interface View {
        fun setAdapter()
        fun showList()
        fun showLoadingView()
        fun setToolBarTitle()
        fun startContentDetailsActivity()
    }

    interface ViewModel

    interface Logic {
        fun event(event: ContentListEvent)
    }
}

sealed class ContentListEvent {
    data class OnListItemClick<out T>(val content: T) : ContentListEvent()
    object OnListRefresh : ContentListEvent()
    object OnStart : ContentListEvent()
    data class OnBind(val view: IContentListContract.View, val viewModel: IContentListContract.ViewModel) : ContentListEvent()
    object OnDestroy : ContentListEvent()
}