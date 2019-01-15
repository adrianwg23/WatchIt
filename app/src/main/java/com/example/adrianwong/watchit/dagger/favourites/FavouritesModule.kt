package com.example.adrianwong.watchit.dagger.favourites

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.favourites.FavouritesFragment
import com.example.adrianwong.watchit.favourites.FavouritesLogic
import com.example.adrianwong.watchit.favourites.IFavouritesContract
import dagger.Module
import dagger.Provides

@Module
class FavouritesModule(private val view: IFavouritesContract.View,
                       private val favouritesVM: IFavouritesContract.ViewModel,
                       private val removedFavouritesVM: IFavouritesContract.ViewModel) {

    @Provides
    @FavouritesScope
    fun providesFavouritesLogic(): IContentListContract.Logic {
        return FavouritesLogic(DispatcherProvider, view, favouritesVM, removedFavouritesVM)
    }

    @Provides
    @FavouritesScope
    fun providesFavouritesAdapter(logic: IContentListContract.Logic): ContentListAdapter {
        return ContentListAdapter(logic, view as FavouritesFragment)
    }
}