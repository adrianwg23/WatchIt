package com.example.adrianwong.watchit.contentlist.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.common.makeToast
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.ContentListViewModel
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Content
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieListFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var movieListLogic: IContentListContract.Logic
    @Inject lateinit var contentListAdapter: ContentListAdapter
    private lateinit var contentListViewModel: ContentListViewModel

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!movieRecyclerView.canScrollVertically(1)) {
                movieListLogic.event(ContentListEvent.OnLoadMoreData)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        contentListViewModel = ViewModelProviders.of(activity!!).get(ContentListViewModel::class.java)
        (activity?.application as MovieApplication).createMoviesComponent(this, contentListViewModel).inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieListLogic.event(ContentListEvent.OnBind)

        /**
         * observe list of movies from ViewModel
         */
        contentListViewModel.movies.observe(this, Observer { movies ->
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
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        progressBar.visibility = View.GONE
    }

    override fun showError(error: String) {
        activity?.makeToast(error)
    }


    override fun setToolBarTitle() {
        activity?.setTitle(R.string.title_movies)
    }

    override fun startContentDetailsActivity(content: Content, view: View) {
        val bundle = bundleOf(getString(R.string.content_extra) to content)

        val transitionImage: View? = view.findViewById(R.id.contentPosterThumbnail)
        var options: ActivityOptionsCompat? = null
        transitionImage?.let {
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity!!,
                    Pair.create(it, getString(R.string.poster_transition))
            )
        }
        val extras = ActivityNavigatorExtras(options)
        findNavController().navigate(R.id.action_navigation_movies_to_contentDetails, bundle, null, extras)
    }
}
