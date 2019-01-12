package com.example.adrianwong.watchit.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesFragment : Fragment() {

    override fun onAttach(context: Context) {
        (activity?.application as MovieApplication).createFavouritesComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupGui()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onDestroy() {
        (activity?.application as MovieApplication).releaseFavouritesComponent()
        super.onDestroy()
    }

    private fun setupGui() {
        activity!!.setTitle(R.string.title_favourites)
    }
}
