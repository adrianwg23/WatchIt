package com.example.adrianwong.watchit.contentlist.movielist

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.common.Mapper
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.usecases.GetPopularMovies
import com.example.adrianwong.domain.usecases.RemoveFavouriteMovie
import com.example.adrianwong.domain.usecases.SaveFavouriteMovie
import com.example.adrianwong.watchit.common.notifyObserver
import com.example.adrianwong.watchit.contentlist.ContentListLogic
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListLogic(dispatcher: DispatcherProvider,
                     view: IContentListContract.View,
                     viewModel: IContentListContract.ViewModel,
                     private val entityToMovieMapper: Mapper<MovieEntity, Movie>,
                     private val movieToEntityMapper: Mapper<Movie, MovieEntity>,
                     private val getPopularMovies: GetPopularMovies,
                     private val saveFavouriteMovie: SaveFavouriteMovie,
                     private val removeFavouriteMovie: RemoveFavouriteMovie) : ContentListLogic<Movie>(dispatcher, view, viewModel) {

    override fun onListItemClick(content: Movie) {
    }

    override fun onItemFavourited(position: Int) {
        val movie = mViewModel.movies.value!![position]
        movie.isFavourite = !movie.isFavourite

        if(movie.isFavourite) {
            saveMovie(movie)
        } else {
            removeMovie(movie)
        }
    }

    override fun onListRefresh() {
    }

    override fun onLoadMoreData() {
        updateMovieList(mViewModel.moviesPageNumber++)
    }

    override fun onStart() {
        jobTracker = Job()

        if (mViewModel.movies.value.isNullOrEmpty()) {
            updateMovieList(mViewModel.moviesPageNumber++)
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
                entityToMovieMapper.mapFrom(movieEntity)
            }
        }

        mViewModel.movies.value?.run {
            addAll(movies)
            mViewModel.movies.notifyObserver()
        } ?: run {
            mViewModel.movies.value = movies.toMutableList()
        }
    }

    private fun saveMovie(movie: Movie) = launch {
        withContext(dispatcher.provideIOContext()) {
            saveFavouriteMovie.execute(movieToEntityMapper.mapFrom(movie))
        }
    }

    private fun removeMovie(movie: Movie) = launch {
        withContext(dispatcher.provideIOContext()) {
            removeFavouriteMovie.execute(movieToEntityMapper.mapFrom(movie))
        }
    }
}