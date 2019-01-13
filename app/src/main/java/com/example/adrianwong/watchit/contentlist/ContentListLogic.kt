package com.example.adrianwong.watchit.contentlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.watchit.common.BaseLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class ContentListLogic<T>(dispatcher: DispatcherProvider,
                                var mView: IContentListContract.View? = null,
                                var mViewModel: IContentListContract.ViewModel<T>? = null) :
    BaseLogic(dispatcher), IContentListContract.Logic, CoroutineScope {

    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker



    override fun event(event: ContentListEvent) {
        when(event) {
            is ContentListEvent.OnListItemClick<*> -> onListItemClick()
            is ContentListEvent.OnListRefresh -> onListRefresh()
            is ContentListEvent.OnStart -> onStart()
            is ContentListEvent.OnBind<*> -> onBind(event.view, event.viewModel as IContentListContract.ViewModel<T>)
            is ContentListEvent.OnDestroy -> onDestroy()
        }
    }

    protected abstract fun onListItemClick()

    protected abstract fun onListRefresh()

    protected abstract fun onStart()

    protected abstract fun onBind(view: IContentListContract.View, viewModel: IContentListContract.ViewModel<T>)

    private fun onDestroy() {
        jobTracker.cancel()
    }
}