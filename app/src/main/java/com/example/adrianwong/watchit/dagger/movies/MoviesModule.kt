package com.example.adrianwong.watchit.dagger.movies

import com.example.adrianwong.domain.repository.IMovieRepository
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.movielist.MovieListFragment
import com.example.adrianwong.watchit.contentlist.movielist.MovieListLogic
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MoviesModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @MoviesScope
        fun providesGetPopularMovies(movieRepository: IMovieRepository): GetPopularMovies {
            return GetPopularMovies(movieRepository)
        }

        @JvmStatic
        @Provides
        @MoviesScope
        fun providesSearchMovie(movieRepository: IMovieRepository): SearchMovie {
            return SearchMovie(movieRepository)
        }

        @JvmStatic
        @Provides
        @MoviesScope
        fun providesMovieListAdapter(movieListLogic: IContentListContract.Logic,
                                     view: IContentListContract.View): ContentListAdapter {
            return ContentListAdapter(movieListLogic, view as MovieListFragment)
        }
    }

    @Binds
    @MoviesScope
    abstract fun bindsIContentListContractLogic(movieListLogic: MovieListLogic): IContentListContract.Logic

}