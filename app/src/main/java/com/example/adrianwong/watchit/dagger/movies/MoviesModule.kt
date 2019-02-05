package com.example.adrianwong.watchit.dagger.movies

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.movielist.MovieListFragment
import com.example.adrianwong.watchit.contentlist.movielist.MovieListLogic
import dagger.Module
import dagger.Provides

@Module
class MoviesModule(private val view: IContentListContract.View, private val viewModel: IContentListContract.ViewModel) {

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
    fun providesMovieListAdapter(movieListLogic: IContentListContract.Logic): ContentListAdapter {
        return ContentListAdapter(movieListLogic, view as MovieListFragment)
    }

    @Provides
    @MoviesScope
    fun providesMovieListLogic(getPopularMovies: GetPopularMovies): IContentListContract.Logic {
        return MovieListLogic(DispatcherProvider, view, viewModel, getPopularMovies)
    }
}