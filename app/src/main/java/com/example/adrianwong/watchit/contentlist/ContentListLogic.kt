package com.example.adrianwong.watchit.contentlist

import android.view.View
import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.watchit.common.BaseLogic
import com.example.adrianwong.watchit.entities.Content
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class ContentListLogic<T>(dispatcher: DispatcherProvider,
                            var mView: IContentListContract.View,
                            var mViewModel: IContentListContract.ViewModel) :
    BaseLogic(dispatcher), IContentListContract.Logic, CoroutineScope {

    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    override fun event(event: ContentListEvent) {
        when(event) {
            is ContentListEvent.OnListItemClick -> onListItemClick(event.content, event.view)
            is ContentListEvent.OnListRefresh -> onListRefresh()
            is ContentListEvent.OnLoadMoreData -> onLoadMoreData()
            is ContentListEvent.OnStart -> onStart()
            is ContentListEvent.OnBind -> onBind()
            is ContentListEvent.OnDestroy -> onDestroy()
        }
    }

    protected abstract fun onListItemClick(content: Content, view: View)

    protected abstract fun onListRefresh()

    protected abstract fun onLoadMoreData()

    protected abstract fun onStart()

    protected abstract fun onBind()

    private fun onDestroy() {
        jobTracker.cancel()
    }
}