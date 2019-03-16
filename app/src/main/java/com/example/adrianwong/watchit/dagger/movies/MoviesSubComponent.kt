package com.example.adrianwong.watchit.dagger.movies

import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.movielist.MovieListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@MoviesScope
@Subcomponent(modules = [MoviesModule::class])
interface MoviesSubComponent {
    fun inject(movieListFragment: MovieListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MoviesSubComponent

        @BindsInstance
        fun view(view: IContentListContract.View): Builder

        @BindsInstance
        fun viewModel(viewModel: IContentListContract.ViewModel): Builder
    }
}