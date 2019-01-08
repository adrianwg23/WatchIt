package com.example.adrianwong.watchit.contentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adrianwong.watchit.R


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupGui()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    private fun setupGui() {
        activity!!.setTitle(R.string.title_movies)
    }
}
