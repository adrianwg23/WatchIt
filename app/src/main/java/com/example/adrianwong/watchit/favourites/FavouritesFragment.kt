package com.example.adrianwong.watchit.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import kotlinx.android.synthetic.main.fragment_favourites.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesFragment : Fragment(), IFavouritesContract.View {

    @Inject lateinit var logic: IFavouritesContract.Logic
    @Inject lateinit var contentAdapter: ContentListAdapter
    private lateinit var favouritesViewModel: IFavouritesContract.ViewModel
    private lateinit var removedFavouritesViewModel: IFavouritesContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        favouritesViewModel = ViewModelProviders.of(activity!!).get(FavouritesViewModel::class.java)
        removedFavouritesViewModel = ViewModelProviders.of(activity!!).get(RemovedFavouritesViewModel::class.java)
        (activity?.application as MovieApplication)
            .createFavouritesComponent(this, favouritesViewModel, removedFavouritesViewModel)
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logic.event(FavouritesEvent.OnBind)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        logic.event(FavouritesEvent.OnStart)
        super.onStart()
    }

    override fun onDestroyView() {
        (activity?.application as MovieApplication).releaseFavouritesComponent()
        super.onDestroyView()
    }

    override fun setAdapter() {
        favouritesRecyclerview.adapter = contentAdapter
    }

    override fun showLoadingView() {
    }

    override fun setToolBarTitle() {
        activity!!.setTitle(R.string.title_favourites)
    }
}
