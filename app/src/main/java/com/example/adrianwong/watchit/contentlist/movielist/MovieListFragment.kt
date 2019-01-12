package com.example.adrianwong.watchit.contentlist.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListAdapter
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListLogic
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieListFragment : Fragment(), IContentListContract.View {

    @Inject lateinit var tvShowListAdapter: TvShowListAdapter
    @Inject lateinit var tvShowListLogic: TvShowListLogic
    @Inject lateinit var factory: MovieListVMFactory
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onAttach(context: Context) {
        (activity?.application as MovieApplication).createMoviesComponent(this, movieListViewModel).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onDestroy() {
        (activity?.application as MovieApplication).releaseMoviesComponent()
        super.onDestroy()
    }

    override fun setAdapater() {


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
