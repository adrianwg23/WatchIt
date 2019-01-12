package com.example.adrianwong.watchit.dagger.movies

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.movielist.MovieListAdapter
import com.example.adrianwong.watchit.contentlist.movielist.MovieListLogic
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {

    @Provides
    @MoviesScope
    fun providesGetPopularMovies(movieRepository: IMovieRepository): GetPopularMovies {
        return GetPopularMovies(movieRepository)
    }

    @Provides
    @MoviesScope
    fun providesSearchMovie(movieRepository: IMovieRepository): SearchMovie {
        return SearchMovie(movieRepository)
    }

    @Provides
    @MoviesScope
    fun providesMovieListAdapter(movieListLogic: IContentListContract.Logic): MovieListAdapter {
        return MovieListAdapter(movieListLogic)
    }

    @Provides
    @MoviesScope
    fun providesMovieListLogic(getPopularMovies: GetPopularMovies, searchMovie: SearchMovie): IContentListContract.Logic {
        return MovieListLogic(DispatcherProvider, getPopularMovies, searchMovie)
    }
}