package com.example.adrianwong.watchit.dagger.favourites

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.domain.usecases.GetFavouriteMovies
import com.example.adrianwong.domain.usecases.GetFavouriteTvShows
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.favourites.FavouritesFragment
import com.example.adrianwong.watchit.favourites.FavouritesLogic
import dagger.Module
import dagger.Provides

@Module
class FavouritesModule(private val view: IContentListContract.View,
                       private val viewModel: IContentListContract.ViewModel) {

    @Provides
    @FavouritesScope
    fun providesGetFavouriteMovies(movieRepository: IMovieRepository): GetFavouriteMovies {
        return GetFavouriteMovies(movieRepository)
    }

    @Provides
    @FavouritesScope
    fun providesGetFavouriteTvShows(tvShowRepository: ITvShowRepository): GetFavouriteTvShows {
        return GetFavouriteTvShows(tvShowRepository)
    }

    @Provides
    @FavouritesScope
    fun providesFavouritesLogic(getFavouriteMovies: GetFavouriteMovies, getFavouriteTvShows: GetFavouriteTvShows): IContentListContract.Logic {
        return FavouritesLogic(DispatcherProvider, view, viewModel, getFavouriteMovies, getFavouriteTvShows)
    }

    @Provides
    @FavouritesScope
    fun providesFavouritesAdapter(logic: IContentListContract.Logic): ContentListAdapter {
        return ContentListAdapter(logic, view as FavouritesFragment)
    }
}