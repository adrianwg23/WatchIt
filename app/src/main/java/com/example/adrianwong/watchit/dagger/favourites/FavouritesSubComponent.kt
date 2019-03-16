package com.example.adrianwong.watchit.dagger.favourites

import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.favourites.FavouritesFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FavouritesScope
@Subcomponent(modules = [FavouritesModule::class])
interface FavouritesSubComponent {
    fun inject(favouritesFragment: FavouritesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): FavouritesSubComponent

        @BindsInstance
        fun view(view: IContentListContract.View): Builder

        @BindsInstance
        fun viewModel(viewModel: IContentListContract.ViewModel): Builder
    }
}