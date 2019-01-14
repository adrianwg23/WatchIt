package com.example.adrianwong.watchit.contentlist

import androidx.lifecycle.MutableLiveData

interface IContentListContract {

    interface View {
        fun setAdapter()
        fun showLoadingView()
        fun setToolBarTitle()
        fun startContentDetailsActivity()
    }

    interface ViewModel<T> {
        val content: MutableLiveData<List<T>>
        var pageNumber: Int
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