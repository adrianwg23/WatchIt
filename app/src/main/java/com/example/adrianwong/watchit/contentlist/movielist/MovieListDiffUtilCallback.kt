package com.example.adrianwong.watchit.contentlist.movielist

import androidx.recyclerview.widget.DiffUtil
import com.example.adrianwong.watchit.entities.Movie

class MovieListDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

}
