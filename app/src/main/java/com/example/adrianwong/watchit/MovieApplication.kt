package com.example.adrianwong.watchit

import android.app.Application
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.dagger.DaggerMainComponent
import com.example.adrianwong.watchit.dagger.MainComponent
import com.example.adrianwong.watchit.dagger.favourites.FavouritesModule
import com.example.adrianwong.watchit.dagger.favourites.FavouritesSubComponent
import com.example.adrianwong.watchit.dagger.modules.AppModule
import com.example.adrianwong.watchit.dagger.modules.DataModule
import com.example.adrianwong.watchit.dagger.modules.NetworkModule
import com.example.adrianwong.watchit.dagger.movies.MoviesModule
import com.example.adrianwong.watchit.dagger.movies.MoviesSubComponent
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsModule
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsSubComponent

class MovieApplication : Application() {

    private lateinit var mainComponent: MainComponent
    private var favouritesSubComponent: FavouritesSubComponent? = null
    private var moviesSubComponent: MoviesSubComponent? = null
    private var tvShowsSubComponent: TvShowsSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule("http://google.com/"))
            .dataModule(DataModule())
            .build()
    }

    fun createFavouritesComponent(): FavouritesSubComponent {
        favouritesSubComponent = mainComponent.plus(FavouritesModule())
        return favouritesSubComponent!!
    }

    fun releaseFavouritesComponent() {
        favouritesSubComponent = null
    }

    fun createMoviesComponent(): MoviesSubComponent {
        moviesSubComponent = mainComponent.plus(MoviesModule())
        return moviesSubComponent!!
    }

    fun releaseMoviesComponent() {
        moviesSubComponent = null
    }

    fun createTvShowsComponent(): TvShowsSubComponent {
        tvShowsSubComponent = mainComponent.plus(TvShowsModule())
        return tvShowsSubComponent!!
    }

    fun releaseTvShowsComponent() {
        tvShowsSubComponent = null
    }

}