package com.example.adrianwong.watchit.dagger

import android.content.Context
import com.example.adrianwong.watchit.dagger.contentdetails.ContentDetailsSubComponent
import com.example.adrianwong.watchit.dagger.favourites.FavouritesSubComponent
import com.example.adrianwong.watchit.dagger.application.AppModule
import com.example.adrianwong.watchit.dagger.application.ApplicationScope
import com.example.adrianwong.watchit.dagger.application.DataModule
import com.example.adrianwong.watchit.dagger.application.NetworkModule
import com.example.adrianwong.watchit.dagger.movies.MoviesSubComponent
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsSubComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface MainComponent {
    fun contentDetailsSubcomponent(): ContentDetailsSubComponent.Builder
    fun moviesSubcomponent(): MoviesSubComponent.Builder
    fun tvShowsSubcomponent(): TvShowsSubComponent.Builder
    fun favouritesSubcomponent(): FavouritesSubComponent.Builder

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        @BindsInstance
        fun applicationContext(context: Context): Builder

        @BindsInstance
        fun baseUrl(url: String): Builder
    }
}