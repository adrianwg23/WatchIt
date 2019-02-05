package com.example.adrianwong.watchit.contentdetails

import androidx.lifecycle.MutableLiveData
import com.example.adrianwong.watchit.entities.Content

interface IContentDetailsContract {

    interface View {
        fun setupUi()
        fun showError(error: String)
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
    data class OnItemFavourited(val content: Content) : ContentDetailsEvent()
    data class OnStart(val content: Content) : ContentDetailsEvent()
    object OnBind : ContentDetailsEvent()
    object OnDestroy : ContentDetailsEvent()
}