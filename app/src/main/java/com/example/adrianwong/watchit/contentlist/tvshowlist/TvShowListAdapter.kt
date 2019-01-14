package com.example.adrianwong.watchit.contentlist.tvshowlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.contentlist.ContentListEvent
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.entities.TvShow
import kotlinx.android.synthetic.main.content_list_item.view.*


class TvShowListAdapter(private val logic: IContentListContract.Logic, private val context: TvShowListFragment) :
    ListAdapter<TvShow, TvShowListAdapter.TvShowViewHolder>(TvShowListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowListAdapter.TvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TvShowViewHolder(inflater.inflate(R.layout.content_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: TvShowListAdapter.TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow, context,
            favouritesClickListener = { logic.event(ContentListEvent.OnItemFavourited(it)) },
            itemClickListener = { logic.event(ContentListEvent.OnListItemClick(it)) })
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tvShow: TvShow, context: TvShowListFragment, favouritesClickListener: (Int) -> Unit,
                 itemClickListener: (TvShow) -> Unit) {
            itemView.contentTitle.text = tvShow.name
            itemView.contentOverview.text = tvShow.overView
            itemView.contentVoteAverage.text = tvShow.voteAverage.toString()
            setFavouriteIcon(context, tvShow)

            Glide.with(context)
                .load(tvShow.posterPath)
                .into(itemView.contentPosterThumbnail)

            itemView.contentFavourite.setOnClickListener {
                favouritesClickListener(adapterPosition)
                setFavouriteIcon(context, tvShow)
            }
            itemView.setOnClickListener { itemClickListener(tvShow) }
        }

        private fun setFavouriteIcon(context: TvShowListFragment, tvShow: TvShow) {
            if (!tvShow.isFavourite) {
                itemView.contentFavourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24px))
            } else {
                itemView.contentFavourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24px))
            }
        }
    }
}