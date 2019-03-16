package com.example.adrianwong.watchit.dagger.contentdetails

import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.domain.usecases.CheckFavouriteStatus
import com.example.adrianwong.domain.usecases.RemoveFavouriteContent
import com.example.adrianwong.domain.usecases.SaveFavouriteContent
import com.example.adrianwong.watchit.contentdetails.ContentDetailsLogic
import com.example.adrianwong.watchit.contentdetails.IContentDetailsContract
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ContentDetailsModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ContentDetailsScope
        fun providesCheckFavouriteStatus(movieRepository: IMovieRepository,
                                         tvShowRepository: ITvShowRepository): CheckFavouriteStatus {
            return CheckFavouriteStatus(movieRepository, tvShowRepository)
        }

        @JvmStatic
        @Provides
        @ContentDetailsScope
        fun providesSaveFavouriteContent(movieRepository: IMovieRepository,
                                         tvShowRepository: ITvShowRepository): SaveFavouriteContent {
            return SaveFavouriteContent(movieRepository, tvShowRepository)
        }

        @JvmStatic
        @Provides
        @ContentDetailsScope
        fun providesRemoveFavouriteContent(movieRepository: IMovieRepository,
                                           tvShowRepository: ITvShowRepository): RemoveFavouriteContent {
            return RemoveFavouriteContent(movieRepository, tvShowRepository)
        }
    }

    @Binds
    @ContentDetailsScope
    abstract fun bindsIContentDetailsContractLogic(contentDetailsLogic: ContentDetailsLogic): IContentDetailsContract.Logic

}