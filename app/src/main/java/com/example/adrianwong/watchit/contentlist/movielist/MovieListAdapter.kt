package com.example.adrianwong.watchit.contentlist.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adrianwong.domain.domainmodel.Movie
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import kotlinx.android.synthetic.main.content_list_item.view.*

class MovieListAdapter(private var logic: IContentListContract.Logic) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.content_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie) {
            logic.event(ContentListEvent.OnListItemClick(it))
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, clickListener: (Movie) -> Unit) {
            //itemView.content_title.text = movie.title
            itemView.setOnClickListener { clickListener(movie) }
        }
    }
}