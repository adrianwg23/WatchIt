package com.example.adrianwong.watchit.dagger.contentdetails

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.domain.usecases.CheckFavouriteStatus
import com.example.adrianwong.domain.usecases.RemoveFavouriteContent
import com.example.adrianwong.domain.usecases.SaveFavouriteContent
import com.example.adrianwong.watchit.contentdetails.ContentDetailsLogic
import com.example.adrianwong.watchit.contentdetails.IContentDetailsContract
import dagger.Module
import dagger.Provides

@Module
class ContentDetailsModule(private val view: IContentDetailsContract.View,
                           private val viewModel: IContentDetailsContract.ViewModel) {

    @Provides
    @ContentDetailsScope
    fun providesCheckFavouriteStatus(movieRepository: IMovieRepository,
                                     tvShowRepository: ITvShowRepository): CheckFavouriteStatus {
        return CheckFavouriteStatus(movieRepository, tvShowRepository)
    }

    @Provides
    @ContentDetailsScope
    fun providesSaveFavouriteContent(movieRepository: IMovieRepository,
                                     tvShowRepository: ITvShowRepository): SaveFavouriteContent {
        return SaveFavouriteContent(movieRepository, tvShowRepository)
    }

    @Provides
    @ContentDetailsScope
    fun providesRemoveFavouriteContent(movieRepository: IMovieRepository,
                                     tvShowRepository: ITvShowRepository): RemoveFavouriteContent {
        return RemoveFavouriteContent(movieRepository, tvShowRepository)
    }

    @Provides
    @ContentDetailsScope
    fun providesContentDetailsLogic(checkFavouriteStatus: CheckFavouriteStatus,
                                    saveFavouriteContent: SaveFavouriteContent,
                                    removeFavouriteContent: RemoveFavouriteContent): IContentDetailsContract.Logic {
        return ContentDetailsLogic(DispatcherProvider, view, viewModel, checkFavouriteStatus, saveFavouriteContent, removeFavouriteContent)
    }

}