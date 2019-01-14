package com.example.adrianwong.watchit.dagger.modules

import android.content.Context
import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.data.db.FavouritesDatabase
import com.example.adrianwong.data.mappers.MovieDataToMovieEntityMapper
import com.example.adrianwong.data.mappers.TvShowDataToTvShowEntityMapper
import com.example.adrianwong.data.repository.MovieRepositoryImpl
import com.example.adrianwong.data.repository.TvShowRepositoryImpl
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.watchit.dagger.MovieApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @MovieApplicationScope
    fun providesIMovieRepository(movieApiService: MovieApiService) : IMovieRepository {
        return MovieRepositoryImpl(movieApiService, MovieDataToMovieEntityMapper)
    }

    @Provides
    @MovieApplicationScope
    fun providesITvShowRepository(movieApiService: MovieApiService) : ITvShowRepository {
        return TvShowRepositoryImpl(movieApiService, TvShowDataToTvShowEntityMapper)
    }

    @Provides
    @MovieApplicationScope
    fun providesRoomDatabase(context: Context): FavouritesDatabase {
        return FavouritesDatabase.getInstance(context)
    }

}