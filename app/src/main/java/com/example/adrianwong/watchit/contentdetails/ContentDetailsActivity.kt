package com.example.adrianwong.watchit.contentdetails

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.transition.addListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.adrianwong.watchit.MovieApplication
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.common.load
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.entities.Content
import kotlinx.android.synthetic.main.activity_content_details.*
import javax.inject.Inject

class ContentDetailsActivity : AppCompatActivity(), IContentDetailsContract.View {

    @Inject lateinit var contentDetailsLogic: IContentDetailsContract.Logic
    private lateinit var contentDetailsViewModel: IContentDetailsContract.ViewModel
    private lateinit var content: Content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_details)
        postponeEnterTransition()
        // sets window fullscreen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_FULLSCREEN

        content = intent.getSerializableExtra("content") as Content

        contentDetailsViewModel = ViewModelProviders.of(this).get(ContentDetailsViewModel::class.java)
        (application as MovieApplication).createContentDetailsComponent(this, contentDetailsViewModel).inject(this)

        detailsBackButton.setOnClickListener { finish() }
        detailsPoster.load(content.posterPath ?: "", this)
        startPostponedEnterTransition()

        window.sharedElementEnterTransition.addListener(onEnd = {
            contentDetailsLogic.event(ContentDetailsEvent.OnBind)
        })

        contentDetailsViewModel.favouriteState.observe(this, Observer { state ->
            state?.let { handleFavouriteStateChange(it) }
        })

        detailsFavoriteFab.setOnClickListener { contentDetailsLogic.event(ContentDetailsEvent.OnItemFavourited) }
    }

    override fun onStart() {
        contentDetailsLogic.event(ContentDetailsEvent.OnStart)
        super.onStart()
    }

    override fun onDestroy() {
        contentDetailsLogic.event(ContentDetailsEvent.OnDestroy)
        super.onDestroy()
    }

    override fun setupUi() {
        detailsTitle.text = content.title
        detailsOverview.text = content.overView
        detailsReleaseDate.text = String.format(getString(R.string.release_date, content.releaseDate))
        detailsScore.text = if (content.voteAverage == 0.0) getString(R.string.not_available) else content.voteAverage.toString()

        val transition = Slide()
        transition.excludeTarget(detailsPoster, true)
        transition.duration = 750
        TransitionManager.beginDelayedTransition(detailsRootView, transition)
        detailsTitle.visibility = View.VISIBLE
        detailsReleaseDate.visibility = View.VISIBLE
        detailsScoreLayout.visibility = View.VISIBLE
        detailsOverviewSection.visibility = View.VISIBLE
        detailsFavoriteFab.visibility = View.VISIBLE

        detailsBackdrop.load(content.backDropPath ?: "", this)
    }

    override fun handleFavouriteStateChange(favourite: Boolean) {

    }
}
