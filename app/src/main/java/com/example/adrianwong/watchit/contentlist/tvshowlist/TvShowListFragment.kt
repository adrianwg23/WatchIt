package com.example.adrianwong.watchit.contentlist.tvshowlist


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
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowListFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var tvShowListLogic: IContentListContract.Logic
    @Inject lateinit var contentListAdapter: ContentListAdapter
    private lateinit var contentListViewModel: ContentListViewModel

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!tvShowRecyclerView.canScrollVertically(1)) {
                tvShowListLogic.event(ContentListEvent.OnLoadMoreData)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        contentListViewModel = ViewModelProviders.of(activity!!).get(ContentListViewModel::class.java)
        (activity?.application as MovieApplication).createTvShowsComponent(this, contentListViewModel).inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvShowListLogic.event(ContentListEvent.OnBind)
        contentListViewModel.tvShows.observe(this, Observer { tvShows ->
            tvShows?.let {
                contentListAdapter.submitList(it as MutableList<Content>)
                contentListAdapter.notifyDataSetChanged()
            }
        })

        tvShowRecyclerView.addOnScrollListener(scrollListener)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        tvShowListLogic.event(ContentListEvent.OnStart)
        super.onStart()
    }

    override fun onDestroyView() {
        tvShowListLogic.event(ContentListEvent.OnDestroy)
        (activity?.application as MovieApplication).releaseTvShowsComponent()
        super.onDestroyView()
    }

    override fun setAdapter() {
        tvShowRecyclerView.adapter = contentListAdapter
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
        activity?.setTitle(R.string.title_tv_shows)
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
        findNavController().navigate(R.id.action_navigation_tv_shows_to_contentDetails, bundle, null, extras)
    }
}
