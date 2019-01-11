package com.example.adrianwong.watchit.dagger.movies

import com.example.adrianwong.watchit.contentlist.movielist.MovieListFragment
import com.example.adrianwong.watchit.dagger.MainComponent
import dagger.Component

@MoviesScope
@Component(modules = [MoviesModule::class], dependencies = [MainComponent::class])
interface MoviesComponent {

    fun inject(movieListFragment: MovieListFragment)
}