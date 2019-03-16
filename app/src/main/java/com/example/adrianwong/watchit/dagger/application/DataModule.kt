package com.example.adrianwong.watchit.dagger.application

import android.content.Context
import com.example.adrianwong.data.db.FavouritesDatabase
import com.example.adrianwong.data.repository.MovieRepositoryImpl
import com.example.adrianwong.data.repository.TvShowRepositoryImpl
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ApplicationScope
        fun providesRoomDatabase(context: Context): FavouritesDatabase {
            return FavouritesDatabase.getInstance(context)
        }
    }

    @Binds
    @ApplicationScope
    abstract fun bindsIMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): IMovieRepository

    @Binds
    @ApplicationScope
    abstract fun bindsITvShowRepository(tvShowRepositoryImpl: TvShowRepositoryImpl): ITvShowRepository

}