package com.example.adrianwong.watchit.contentlist


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.adrianwong.watchit.R


/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity!!.setTitle(R.string.title_tv_shows)

        Log.d("henlo", "TVFRAGMENT ONCREATE")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }


}
