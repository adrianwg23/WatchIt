package com.example.adrianwong.watchit.contentlist.tvshowlist


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
import com.example.adrianwong.watchit.entities.TvShow
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowListFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var tvShowListLogic: IContentListContract.Logic
    @Inject lateinit var tvShowListAdapter: TvShowListAdapter
    private lateinit var tvShowsListViewModel: IContentListContract.ViewModel<TvShow>

    override fun onCreate(savedInstanceState: Bundle?) {
        tvShowsListViewModel = ViewModelProviders.of(activity!!).get(TvShowListViewModel::class.java)
        (activity?.application as MovieApplication).createTvShowsComponent(this, tvShowsListViewModel).inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvShowListLogic.event(ContentListEvent.OnBind)
        tvShowsListViewModel.content.observe(this, Observer { tvShows ->
            tvShows?.let {
                tvShowListAdapter.submitList(it)
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        tvShowListLogic.event(ContentListEvent.OnStart)
        super.onStart()
    }

    override fun onDestroy() {
        tvShowListLogic.event(ContentListEvent.OnDestroy)
        (activity?.application as MovieApplication).releaseTvShowsComponent()
        super.onDestroy()
    }

    override fun setAdapter() {
        tvShowRecyclerView.adapter = tvShowListAdapter
    }

    override fun showLoadingView() {
    }

    override fun setToolBarTitle() {
        activity!!.setTitle(R.string.title_tv_shows)
    }

    override fun startContentDetailsActivity() {
    }

}
