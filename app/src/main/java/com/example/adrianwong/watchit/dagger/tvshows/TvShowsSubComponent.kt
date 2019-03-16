package com.example.adrianwong.watchit.dagger.tvshows

import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@TvShowsScope
@Subcomponent(modules = [TvShowsModule::class])
interface TvShowsSubComponent {
    fun inject(tvShowListFragment: TvShowListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): TvShowsSubComponent

        @BindsInstance
        fun view(view: IContentListContract.View): Builder

        @BindsInstance
        fun viewModel(viewModel: IContentListContract.ViewModel): Builder
    }
}