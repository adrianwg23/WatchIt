package com.example.adrianwong.watchit.contentlist.movielist

import android.content.Context
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieListFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var movieListLogic: IContentListContract.Logic
    @Inject lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onAttach(context: Context) {
        (activity?.application as MovieApplication).createMoviesComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        movieListLogic.event(ContentListEvent.OnBind(this, movieListViewModel))
        movieListViewModel.movies.observe(this, Observer { movies ->
            movies?.let {
                movieListAdapter.submitList(it)
            }
        })
    }

    override fun onStart() {
        movieListLogic.event(ContentListEvent.OnStart)
        super.onStart()
    }

    override fun onDestroy() {
        movieListLogic.event(ContentListEvent.OnDestroy)
        (activity?.application as MovieApplication).releaseMoviesComponent()
        super.onDestroy()
    }

    override fun setAdapter() {
        movieRecyclerView.adapter = movieListAdapter
    }

    override fun showList() {
    }

    override fun showLoadingView() {
    }

    override fun setToolBarTitle() {
        activity!!.setTitle(R.string.title_movies)
    }

    override fun startContentDetailsActivity() {
    }


}
