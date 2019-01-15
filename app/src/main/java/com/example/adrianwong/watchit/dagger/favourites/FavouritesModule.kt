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
import com.example.adrianwong.watchit.favourites.IFavouritesContract
import com.example.adrianwong.watchit.mappers.MovieEntityToMovieMapper
import com.example.adrianwong.watchit.mappers.TvShowEntityToTvShowMapper
import dagger.Module
import dagger.Provides

@Module
class FavouritesModule(private val view: IFavouritesContract.View,
                       private val favouritesVM: IFavouritesContract.ViewModel,
                       private val removedFavouritesVM: IFavouritesContract.ViewModel) {

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
        return FavouritesLogic(DispatcherProvider, MovieEntityToMovieMapper, TvShowEntityToTvShowMapper,
            getFavouriteMovies, getFavouriteTvShows, view, favouritesVM, removedFavouritesVM)
    }

    @Provides
    @FavouritesScope
    fun providesFavouritesAdapter(logic: IContentListContract.Logic): ContentListAdapter {
        return ContentListAdapter(logic, view as FavouritesFragment)
    }
}