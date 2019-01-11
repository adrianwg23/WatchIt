package com.example.adrianwong.watchit.dagger.favourites

import com.example.adrianwong.watchit.dagger.MainComponent
import dagger.Component

@FavouritesScope
@Component(modules = [FavouritesModule::class], dependencies = [MainComponent::class])
interface FavouritesComponent {

}