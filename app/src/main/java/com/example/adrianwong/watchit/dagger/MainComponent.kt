package com.example.adrianwong.watchit.dagger

import com.example.adrianwong.watchit.dagger.favourites.FavouritesModule
import com.example.adrianwong.watchit.dagger.favourites.FavouritesSubComponent
import com.example.adrianwong.watchit.dagger.modules.AppModule
import com.example.adrianwong.watchit.dagger.modules.DataModule
import com.example.adrianwong.watchit.dagger.modules.NetworkModule
import com.example.adrianwong.watchit.dagger.movies.MoviesModule
import com.example.adrianwong.watchit.dagger.movies.MoviesSubComponent
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsModule
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsSubComponent
import dagger.Component

@MovieApplicationScope
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface MainComponent {
    fun plus(moviesModule: MoviesModule): MoviesSubComponent
    fun plus(tvShowsModule: TvShowsModule): TvShowsSubComponent
    fun plus(favouritesModule: FavouritesModule): FavouritesSubComponent
}