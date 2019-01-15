package com.example.adrianwong.watchit.dagger.modules

import android.content.Context
import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.data.db.FavouritesDatabase
import com.example.adrianwong.data.mappers.MovieDataToMovieEntityMapper
import com.example.adrianwong.data.mappers.MovieEntityToMovieDataMapper
import com.example.adrianwong.data.mappers.TvShowDataToTvShowEntityMapper
import com.example.adrianwong.data.mappers.TvShowEntityToTvShowDataMapper
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
    fun providesIMovieRepository(movieApiService: MovieApiService, database: FavouritesDatabase) : IMovieRepository {
        return MovieRepositoryImpl(movieApiService, database, MovieDataToMovieEntityMapper, MovieEntityToMovieDataMapper)
    }

    @Provides
    @MovieApplicationScope
    fun providesITvShowRepository(movieApiService: MovieApiService, database: FavouritesDatabase) : ITvShowRepository {
        return TvShowRepositoryImpl(movieApiService, database, TvShowDataToTvShowEntityMapper, TvShowEntityToTvShowDataMapper)
    }

    @Provides
    @MovieApplicationScope
    fun providesRoomDatabase(context: Context): FavouritesDatabase {
        return FavouritesDatabase.getInstance(context)
    }

}