package com.example.adrianwong.watchit

import android.app.Application
import com.example.adrianwong.watchit.contentdetails.IContentDetailsContract
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.dagger.DaggerMainComponent
import com.example.adrianwong.watchit.dagger.MainComponent
import com.example.adrianwong.watchit.dagger.contentdetails.ContentDetailsModule
import com.example.adrianwong.watchit.dagger.contentdetails.ContentDetailsSubComponent
import com.example.adrianwong.watchit.dagger.favourites.FavouritesModule
import com.example.adrianwong.watchit.dagger.favourites.FavouritesSubComponent
import com.example.adrianwong.watchit.dagger.modules.AppModule
import com.example.adrianwong.watchit.dagger.modules.DataModule
import com.example.adrianwong.watchit.dagger.modules.NetworkModule
import com.example.adrianwong.watchit.dagger.movies.MoviesModule
import com.example.adrianwong.watchit.dagger.movies.MoviesSubComponent
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsModule
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsSubComponent
import com.example.adrianwong.watchit.favourites.IFavouritesContract

class MovieApplication : Application() {

    private lateinit var mainComponent: MainComponent
    private var contentDetailsSubComponent: ContentDetailsSubComponent? = null
    private var favouritesSubComponent: FavouritesSubComponent? = null
    private var moviesSubComponent: MoviesSubComponent? = null
    private var tvShowsSubComponent: TvShowsSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(getString(R.string.base_url)))
            .dataModule(DataModule())
            .build()
    }

    fun createContentDetailsComponent(view: IContentDetailsContract.View,
                                      viewModel: IContentDetailsContract.ViewModel): ContentDetailsSubComponent {
        contentDetailsSubComponent = mainComponent.plus(ContentDetailsModule(view, viewModel))
        return contentDetailsSubComponent!!
    }

    fun releaseContentDetailsComponent() {
        contentDetailsSubComponent = null
    }

    fun createFavouritesComponent(view: IFavouritesContract.View,
                                  favouritesVM: IFavouritesContract.ViewModel): FavouritesSubComponent {
        favouritesSubComponent = mainComponent.plus(FavouritesModule(view, favouritesVM))
        return favouritesSubComponent!!
    }

    fun releaseFavouritesComponent() {
        favouritesSubComponent = null
    }

    fun createMoviesComponent(view: IContentListContract.View, viewModel: IContentListContract.ViewModel): MoviesSubComponent {
        moviesSubComponent = mainComponent.plus(MoviesModule(view, viewModel))
        return moviesSubComponent!!
    }

    fun releaseMoviesComponent() {
        moviesSubComponent = null
    }

    fun createTvShowsComponent(view: IContentListContract.View, viewModel: IContentListContract.ViewModel): TvShowsSubComponent {
        tvShowsSubComponent = mainComponent.plus(TvShowsModule(view, viewModel))
        return tvShowsSubComponent!!
    }

    fun releaseTvShowsComponent() {
        tvShowsSubComponent = null
    }
}