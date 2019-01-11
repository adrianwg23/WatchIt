package com.example.adrianwong.watchit.dagger.tvshows

import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListFragment
import com.example.adrianwong.watchit.dagger.MainComponent
import dagger.Component

@TvShowsScope
@Component(modules = [TvShowsModule::class], dependencies = [MainComponent::class])
interface TvShowsComponent {

    fun inject(tvShowListFragment: TvShowListFragment)
}