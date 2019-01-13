package com.example.adrianwong.watchit.contentlist

import androidx.lifecycle.MutableLiveData

interface IContentListContract {

    interface View {
        fun setAdapter()
        fun showList()
        fun showLoadingView()
        fun setToolBarTitle()
        fun startContentDetailsActivity()
    }

    interface ViewModel<T> {
        val content: MutableLiveData<List<T>>
    }

    interface Logic {
        fun event(event: ContentListEvent)
    }
}

sealed class ContentListEvent {
    data class OnListItemClick<out T>(val content: T) : ContentListEvent()
    object OnListRefresh : ContentListEvent()
    object OnStart : ContentListEvent()
    data class OnBind<T>(val view: IContentListContract.View, val viewModel: IContentListContract.ViewModel<T>) : ContentListEvent()
    object OnDestroy : ContentListEvent()
}