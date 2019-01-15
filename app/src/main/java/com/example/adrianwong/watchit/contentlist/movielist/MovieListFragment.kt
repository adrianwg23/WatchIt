package com.example.adrianwong.watchit.contentlist.movielist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieListFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var movieListLogic: IContentListContract.Logic
    @Inject lateinit var contentListAdapter: ContentListAdapter
    private lateinit var movieListViewModel: IContentListContract.ViewModel<Movie>

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!movieRecyclerView.canScrollVertically(1)) {
                movieListLogic.event(ContentListEvent.OnLoadMoreData)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.content, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.movies -> {
                Toast.makeText(activity, "movies", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.tvShows -> {
                Toast.makeText(activity, "tvshows", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

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
                contentListAdapter.submitList(it as MutableList<Content>)
                contentListAdapter.notifyDataSetChanged()

            }
        })

        movieRecyclerView.addOnScrollListener(scrollListener)

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
        movieRecyclerView.adapter = contentListAdapter
    }

    override fun showLoadingView() {
    }

    override fun setToolBarTitle() {
        activity!!.setTitle(R.string.title_movies)
    }

    override fun startContentDetailsActivity() {
    }
}
