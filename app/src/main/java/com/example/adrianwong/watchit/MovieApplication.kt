package com.example.adrianwong.watchit

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.adrianwong.watchit.contentdetails.IContentDetailsContract
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.favourites.FavouritesFragment
import com.example.adrianwong.watchit.contentlist.movielist.MovieListFragment
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListFragment
import com.example.adrianwong.watchit.dagger.DaggerMainComponent
import com.example.adrianwong.watchit.dagger.MainComponent
import com.example.adrianwong.watchit.dagger.contentdetails.ContentDetailsSubComponent
import com.example.adrianwong.watchit.dagger.favourites.FavouritesModule
import com.example.adrianwong.watchit.dagger.favourites.FavouritesSubComponent
import com.example.adrianwong.watchit.dagger.movies.MoviesModule
import com.example.adrianwong.watchit.dagger.movies.MoviesSubComponent
import com.example.adrianwong.watchit.dagger.tvshows.TvShowsSubComponent

class MovieApplication : Application() {

    private lateinit var mainComponent: MainComponent
    private var contentDetailsSubComponent: ContentDetailsSubComponent? = null
    private var favouritesSubComponent: FavouritesSubComponent? = null
    private var moviesSubComponent: MoviesSubComponent? = null
    private var tvShowsSubComponent: TvShowsSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
            .applicationContext(applicationContext)
            .baseUrl(getString(R.string.base_url))
            .build()
    }

    fun createContentDetailsComponent(view: IContentDetailsContract.View,
                                      viewModel: IContentDetailsContract.ViewModel): ContentDetailsSubComponent {
        contentDetailsSubComponent = mainComponent
            .contentDetailsSubcomponent()
            .view(view)
            .viewModel(viewModel)
            .build()
        return contentDetailsSubComponent!!
    }

    fun releaseContentDetailsComponent() {
        contentDetailsSubComponent = null
    }

    fun createFavouritesComponent(view: IContentListContract.View,
                                  viewModel: IContentListContract.ViewModel): FavouritesSubComponent {
        favouritesSubComponent = mainComponent
            .favouritesSubcomponent()
            .view(view)
            .viewModel(viewModel)
            .build()
        return favouritesSubComponent!!
    }

    fun releaseFavouritesComponent() {
        favouritesSubComponent = null
    }

    fun createMoviesComponent(view: IContentListContract.View, viewModel: IContentListContract.ViewModel): MoviesSubComponent {
        moviesSubComponent = mainComponent
            .moviesSubcomponent()
            .view(view)
            .viewModel(viewModel)
            .build()
        return moviesSubComponent!!
    }

    fun releaseMoviesComponent() {
        moviesSubComponent = null
    }

    fun createTvShowsComponent(view: IContentListContract.View, viewModel: IContentListContract.ViewModel): TvShowsSubComponent {
        tvShowsSubComponent = mainComponent
            .tvShowsSubcomponent()
            .view(view)
            .viewModel(viewModel)
            .build()
        return tvShowsSubComponent!!
    }

    fun releaseTvShowsComponent() {
        tvShowsSubComponent = null
    }
}