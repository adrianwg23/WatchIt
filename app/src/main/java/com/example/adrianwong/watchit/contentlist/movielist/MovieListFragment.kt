package com.example.adrianwong.watchit.contentlist.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.IContentListContract


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieListFragment : Fragment(), IContentListContract.View {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }
}
