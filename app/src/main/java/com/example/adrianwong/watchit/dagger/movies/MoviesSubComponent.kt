package com.example.adrianwong.watchit.dagger.movies

import com.example.adrianwong.watchit.contentlist.movielist.MovieListFragment
import dagger.Subcomponent

@MoviesScope
@Subcomponent(modules = [MoviesModule::class])
interface MoviesSubComponent {
    fun inject(movieListFragment: MovieListFragment)
}