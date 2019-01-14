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
        holder.bind(movie, context,
            favouritesClickListener = { logic.event(ContentListEvent.OnItemFavourited(it)) },
            itemClickListener = { logic.event(ContentListEvent.OnListItemClick(it)) })
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, context: MovieListFragment, favouritesClickListener:(Int) -> Unit,
                 itemClickListener: (Movie) -> Unit) {
            itemView.contentTitle.text = movie.title
            itemView.contentOverview.text = movie.overView
            itemView.contentVoteAverage.text = movie.voteAverage.toString()
            setFavouriteIcon(context, movie)

            Glide.with(context)
                .load(movie.posterPath)
                .into(itemView.contentPosterThumbnail)

            itemView.contentFavourite.setOnClickListener {
                favouritesClickListener(adapterPosition)
                setFavouriteIcon(context, movie)
            }
            itemView.setOnClickListener { itemClickListener(movie) }
        }

        private fun setFavouriteIcon(context: MovieListFragment, movie: Movie) {
            if (!movie.isFavourite) {
                itemView.contentFavourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24px))
            } else {
                itemView.contentFavourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24px))
            }
        }
    }
}