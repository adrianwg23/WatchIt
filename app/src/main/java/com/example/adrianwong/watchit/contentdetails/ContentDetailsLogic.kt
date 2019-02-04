package com.example.adrianwong.watchit.contentdetails

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.watchit.common.BaseLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ContentDetailsLogic(dispatcher: DispatcherProvider,
                          private val mView: IContentDetailsContract.View,
                          private val mViewModel: IContentDetailsContract.ViewModel) :
    BaseLogic(dispatcher), IContentDetailsContract.Logic, CoroutineScope {

    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    override fun event(event: ContentDetailsEvent) {
        when(event) {
            is ContentDetailsEvent.OnItemFavourited -> onItemFavourited()
            is ContentDetailsEvent.OnStart -> onStart()
            is ContentDetailsEvent.OnBind -> onBind()
            is ContentDetailsEvent.OnDestroy -> onDestroy()
        }
    }

    private fun onItemFavourited() {

    }

    private fun onStart() {

    }

    private fun onBind() {
        mView.setupUi()
    }

    private fun onDestroy() {

    }
}