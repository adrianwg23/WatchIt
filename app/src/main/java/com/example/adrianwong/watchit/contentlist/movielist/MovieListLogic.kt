package com.example.adrianwong.watchit.contentlist.movielist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.SearchMovie
import com.example.adrianwong.watchit.common.notifyObserver
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListLogic(dispatcher: DispatcherProvider,
                     view: IContentListContract.View,
                     viewModel: IContentListContract.ViewModel<Movie>,
                     private val mapper: Mapper<MovieEntity, Movie>,
                     private val getPopularMovies: GetPopularMovies,
                     private val searchMovie: SearchMovie) : ContentListLogic<Movie>(dispatcher, view, viewModel) {

    override fun onListItemClick(content: Movie) {
    }

    override fun onItemFavourited(position: Int) {
        val movie = mViewModel.content.value!![position]
        movie.isFavourite = !movie.isFavourite
    }

    override fun onListRefresh() {
    }

    override fun onLoadMoreData() {
        updateMovieList(mViewModel.pageNumber++)
    }

    override fun onStart() {
        if (mViewModel.content.value.isNullOrEmpty()) {
            updateMovieList(mViewModel.pageNumber++)
        }
    }

    override fun onBind() {
        mView.run {
            setToolBarTitle()
            setAdapter()
        }
    }

    private fun updateMovieList(page: Int) = launch {
        val movies = withContext(dispatcher.provideIOContext()) {
            getPopularMovies.execute(page).map { movieEntity ->
                mapper.mapFrom(movieEntity)
            }
        }

        mViewModel.content.value?.run {
            addAll(movies)
            mViewModel.content.notifyObserver()
        } ?: run {
            mViewModel.content.value = movies.toMutableList()
        }
    }
}