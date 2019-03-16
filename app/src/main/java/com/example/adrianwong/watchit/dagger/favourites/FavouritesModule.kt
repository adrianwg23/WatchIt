package com.example.adrianwong.watchit.dagger.favourites

import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.domain.usecases.GetFavouriteMovies
import com.example.adrianwong.domain.usecases.GetFavouriteTvShows
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.favourites.FavouritesFragment
import com.example.adrianwong.watchit.contentlist.favourites.FavouritesLogic
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class FavouritesModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @FavouritesScope
        fun providesGetFavouriteMovies(movieRepository: IMovieRepository): GetFavouriteMovies {
            return GetFavouriteMovies(movieRepository)
        }

        @JvmStatic
        @Provides
        @FavouritesScope
        fun providesGetFavouriteTvShows(tvShowRepository: ITvShowRepository): GetFavouriteTvShows {
            return GetFavouriteTvShows(tvShowRepository)
        }

        @JvmStatic
        @Provides
        @FavouritesScope
        fun providesFavouritesAdapter(logic: IContentListContract.Logic, view: IContentListContract.View): ContentListAdapter {
            return ContentListAdapter(logic, view as FavouritesFragment)
        }
    }

    @Binds
    @FavouritesScope
    abstract fun bindsIContentlistContractLogic(favouritesLogic: FavouritesLogic): IContentListContract.Logic

}