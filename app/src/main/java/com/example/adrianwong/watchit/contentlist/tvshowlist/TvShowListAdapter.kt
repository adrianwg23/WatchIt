package com.example.adrianwong.watchit.contentlist.tvshowlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.TvShow


class TvShowListAdapter(private var logic: IContentListContract.Logic) :
    ListAdapter<TvShow, TvShowListAdapter.TvShowViewHolder>(TvShowListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowListAdapter.TvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TvShowViewHolder(inflater.inflate(R.layout.content_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: TvShowListAdapter.TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow) {
            logic.event(ContentListEvent.OnListItemClick(it))
        }
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tvShow: TvShow, clickListener: (TvShow) -> Unit) {
            //itemView.content_title.text = tvShowEntity.title
            itemView.setOnClickListener { clickListener(tvShow) }
        }
    }
}