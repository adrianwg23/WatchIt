package com.example.adrianwong.watchit.contentlist.favourites

import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.common.makeToast
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.ContentType
import kotlinx.android.synthetic.main.fragment_favourites.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var favouritesLogic: IContentListContract.Logic
    @Inject lateinit var contentAdapter: ContentListAdapter
    private lateinit var favouritesViewModel: IContentListContract.ViewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.content, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.movies -> {
                favouritesLogic.event(ContentListEvent.OnFavouriteContentChanged(ContentType.MOVIE))
                true
            }
            R.id.tvShows -> {
                favouritesLogic.event(ContentListEvent.OnFavouriteContentChanged(ContentType.TV_SHOW))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        favouritesViewModel = ViewModelProviders.of(activity!!).get(FavouritesViewModel::class.java)
        (activity?.application as MovieApplication)
            .createFavouritesComponent(this, favouritesViewModel)
            .inject(this)

        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        favouritesLogic.event(ContentListEvent.OnBind)

        favouritesViewModel.movies.observe(this, Observer { movies ->
            movies?.let {
                contentAdapter.submitList(it as MutableList<Content>)
            }
        })

        favouritesViewModel.tvShows.observe(this, Observer { tvShows ->
            tvShows?.let {
                contentAdapter.submitList(it as MutableList<Content>)
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        favouritesLogic.event(ContentListEvent.OnStart)
        super.onStart()
    }

    override fun onDestroyView() {
        favouritesLogic.event(ContentListEvent.OnDestroy)
        (activity?.application as MovieApplication).releaseFavouritesComponent()
        super.onDestroyView()
    }

    override fun setAdapter() {
        favouritesRecyclerview.adapter = contentAdapter
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
        activity?.setTitle(R.string.title_favourites)
    }

    override fun startContentDetailsActivity(content: Content, view: View) {
        val bundle = bundleOf("content" to content)

        val transitionImage: View? = view.findViewById(R.id.contentPosterThumbnail)
        var options: ActivityOptionsCompat? = null
        transitionImage?.let {
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity!!,
                Pair.create(it, getString(R.string.poster_transition))
            )
        }
        val extras = ActivityNavigatorExtras(options)
        findNavController().navigate(R.id.action_navigation_favourites_to_contentDetails, bundle, null, extras)
    }
}
