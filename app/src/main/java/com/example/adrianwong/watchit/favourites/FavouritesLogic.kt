package com.example.adrianwong.watchit.favourites

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.watchit.common.BaseLogic

class FavouritesLogic(dispatcher: DispatcherProvider,
                      private val view: IFavouritesContract.View,
                      private val favouritesViewModel: IFavouritesContract.ViewModel,
                      private val removedFavouritesViewModel: IFavouritesContract.ViewModel):
    BaseLogic(dispatcher), IFavouritesContract.Logic {

    override fun event(event: FavouritesEvent) {
        when(event) {
            is FavouritesEvent.OnStart -> onStart()
            is FavouritesEvent.OnBind -> onBind()
            is FavouritesEvent.OnItemFavourited -> onItemFavourite(event.position)
        }
    }

    private fun onStart() {

    }

    private fun onBind() {
        view.run {
            setAdapter()
            setToolBarTitle()
        }
    }

    private fun onItemFavourite(position: Int) {

    }
}