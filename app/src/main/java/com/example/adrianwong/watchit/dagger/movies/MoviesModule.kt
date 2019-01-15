package com.example.adrianwong.watchit.dagger.movies

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.RemoveFavouriteMovie
import com.example.adrianwong.domain.usecases.SaveFavouriteMovie
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.movielist.MovieListFragment
import com.example.adrianwong.watchit.contentlist.movielist.MovieListLogic
import com.example.adrianwong.watchit.mappers.MovieEntityToMovieMapper
import com.example.adrianwong.watchit.mappers.MovieToMovieEntityMapper
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
    fun providesSaveFavouriteMovie(movieRepository: IMovieRepository): SaveFavouriteMovie {
        return SaveFavouriteMovie(movieRepository)
    }

    @Provides
    @MoviesScope
    fun providesRemoveFavouriteMovie(movieRepository: IMovieRepository): RemoveFavouriteMovie {
        return RemoveFavouriteMovie(movieRepository)
    }

    @Provides
    @MoviesScope
    fun providesMovieListAdapter(movieListLogic: IContentListContract.Logic): ContentListAdapter {
        return ContentListAdapter(movieListLogic, view as MovieListFragment)
    }

    @Provides
    @MoviesScope
    fun providesMovieListLogic(getPopularMovies: GetPopularMovies, saveFavouriteMovie: SaveFavouriteMovie,
                               removeFavouriteMovie: RemoveFavouriteMovie): IContentListContract.Logic {
        return MovieListLogic(DispatcherProvider, view, viewModel,
            MovieEntityToMovieMapper, MovieToMovieEntityMapper, getPopularMovies, saveFavouriteMovie, removeFavouriteMovie)
    }
}