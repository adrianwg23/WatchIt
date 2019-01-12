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

    lateinit var mainComponent: MainComponent
    private var favouritesSubComponent: FavouritesSubComponent? = null
    private var moviesSubcomponent: MoviesSubComponent? = null
    private var tvShowsSubComponent: TvShowsSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule("test"))
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

    fun createMoviesComponent(view: IContentListContract.View, viewModel: IContentListContract.MovieListViewModel): MoviesSubComponent {
        moviesSubcomponent = mainComponent.plus(MoviesModule(view, viewModel))
        return moviesSubcomponent!!
    }

    fun releaseMoviesComponent() {
        moviesSubcomponent = null
    }

    fun createTvShowsComponent(view: IContentListContract.View, viewModel: IContentListContract.TvShowListViewModel): TvShowsSubComponent {
        tvShowsSubComponent = mainComponent.plus(TvShowsModule(view, viewModel))
        return tvShowsSubComponent!!
    }

    fun releaseTvShowsComponent() {
        tvShowsSubComponent = null
    }

}