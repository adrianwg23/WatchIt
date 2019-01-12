package com.example.adrianwong.watchit.dagger.tvshows

import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListFragment
import dagger.Subcomponent

@TvShowsScope
@Subcomponent(modules = [TvShowsModule::class])
interface TvShowsSubComponent {
    fun inject(tvShowListFragment: TvShowListFragment)
}