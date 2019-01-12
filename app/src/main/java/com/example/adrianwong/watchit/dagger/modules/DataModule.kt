package com.example.adrianwong.watchit.dagger.modules

import com.example.adrianwong.data.api.MovieApiService
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
        return MovieRepositoryImpl(movieApiService)
    }

    @Provides
    @MovieApplicationScope
    fun providesITvShowRepository(movieApiService: MovieApiService) : ITvShowRepository {
        return TvShowRepositoryImpl(movieApiService)
    }

}