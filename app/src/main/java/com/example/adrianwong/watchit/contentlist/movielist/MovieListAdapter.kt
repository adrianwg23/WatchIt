package com.example.adrianwong.watchit.contentlist.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.Movie
import kotlinx.android.synthetic.main.content_list_item.view.*

class MovieListAdapter(private val logic: IContentListContract.Logic, private val context: MovieListFragment) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.content_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, context) {
            logic.event(ContentListEvent.OnListItemClick(it))
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, context: MovieListFragment, clickListener: (Movie) -> Unit) {
            itemView.contentTitle.text = movie.title
            itemView.contentOverview.text = movie.overView
            itemView.contentVoteAverage.text = movie.voteAverage.toString()

            Glide.with(context)
                .load(movie.posterPath)
                .into(itemView.contentPosterThumbnail)

            itemView.setOnClickListener { clickListener(movie) }
        }
    }
}