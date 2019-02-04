package com.example.adrianwong.watchit.contentdetails

import androidx.lifecycle.MutableLiveData

interface IContentDetailsContract {

    interface View {
        fun setupUi()
        fun handleFavouriteStateChange(favourite: Boolean)
    }

    interface ViewModel {
        val favouriteState: MutableLiveData<Boolean>
    }

    interface Logic {
        fun event(event: ContentDetailsEvent)
    }
}

sealed class ContentDetailsEvent {
    object OnItemFavourited : ContentDetailsEvent()
    object OnStart : ContentDetailsEvent()
    object OnBind : ContentDetailsEvent()
    object OnDestroy : ContentDetailsEvent()
}