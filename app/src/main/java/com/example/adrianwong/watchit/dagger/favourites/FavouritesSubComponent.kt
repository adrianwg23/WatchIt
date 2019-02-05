package com.example.adrianwong.watchit.dagger.favourites

import com.example.adrianwong.watchit.contentlist.favourites.FavouritesFragment
import dagger.Subcomponent

@FavouritesScope
@Subcomponent(modules = [FavouritesModule::class])
interface FavouritesSubComponent {
    fun inject(favouritesFragment: FavouritesFragment)
}