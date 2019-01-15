package com.example.adrianwong.watchit.common

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseLogic<T>(val dispatcher: DispatcherProvider,
                            var mView: IContentListContract.View,
                            var mViewModel: IContentListContract.ViewModel) :  IContentListContract.Logic, CoroutineScope {

    protected lateinit var jobTracker: Job

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker



    override fun event(event: ContentListEvent) {
        when(event) {
            is ContentListEvent.OnListItemClick<*> -> onListItemClick(event.content as T)
            is ContentListEvent.OnItemFavourited -> onItemFavourited(event.position)
            is ContentListEvent.OnListRefresh -> onListRefresh()
            is ContentListEvent.OnLoadMoreData -> onLoadMoreData()
            is ContentListEvent.OnStart -> onStart()
            is ContentListEvent.OnBind -> onBind()
            is ContentListEvent.OnDestroy -> onDestroy()
        }
    }

    protected abstract fun onListItemClick(content: T)

    protected abstract fun onItemFavourited(position: Int)

    protected abstract fun onListRefresh()

    protected abstract fun onLoadMoreData()

    protected abstract fun onStart()

    protected abstract fun onBind()

    private fun onDestroy() {
        jobTracker.cancel()
    }
}