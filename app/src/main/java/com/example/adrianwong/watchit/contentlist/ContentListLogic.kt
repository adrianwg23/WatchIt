package com.example.adrianwong.watchit.contentlist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.domain.usecases.SearchTvShow
import com.example.adrianwong.watchit.common.BaseLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class ContentListLogic(dispatcher: DispatcherProvider,
                                var view: IContentListContract.View? = null,
                                var viewModel: IContentListContract.ViewModel? = null) :
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
            is ContentListEvent.OnBind -> onBind(event.view, event.viewModel)
            is ContentListEvent.OnDestroy -> onDestroy()
        }
    }

    protected abstract fun onListItemClick()

    protected abstract fun onListRefresh()

    protected abstract fun onStart()

    protected abstract fun onBind(view: IContentListContract.View, viewModel: IContentListContract.ViewModel)

    private fun onDestroy() {
        jobTracker.cancel()
    }
}