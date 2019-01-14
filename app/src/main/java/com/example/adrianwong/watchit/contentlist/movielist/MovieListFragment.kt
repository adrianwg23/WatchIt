package com.example.adrianwong.watchit.contentlist.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieListFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var movieListLogic: IContentListContract.Logic
    @Inject lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieListViewModel: IContentListContract.ViewModel<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        movieListViewModel = ViewModelProviders.of(activity!!).get(MovieListViewModel::class.java)
        (activity?.application as MovieApplication).createMoviesComponent(this, movieListViewModel).inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieListLogic.event(ContentListEvent.OnBind)
        movieListViewModel.content.observe(this, Observer { movies ->
            movies?.let {
                movieListAdapter.submitList(it)
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onStart() {
        movieListLogic.event(ContentListEvent.OnStart)
        super.onStart()
    }

    override fun onDestroyView() {
        movieListLogic.event(ContentListEvent.OnDestroy)
        (activity?.application as MovieApplication).releaseMoviesComponent()
        super.onDestroyView()
    }

    override fun setAdapter() {
        movieRecyclerView.adapter = movieListAdapter
    }

    override fun showLoadingView() {
    }

    override fun setToolBarTitle() {
        activity!!.setTitle(R.string.title_movies)
    }

    override fun startContentDetailsActivity() {
    }


}
