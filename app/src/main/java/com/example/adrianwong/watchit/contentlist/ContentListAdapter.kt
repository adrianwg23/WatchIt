package com.example.adrianwong.watchit.contentlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.entities.Content
import kotlinx.android.synthetic.main.content_list_item.view.*


class ContentListAdapter(private val logic: IContentListContract.Logic, private val context: Fragment) :
    ListAdapter<Content, ContentListAdapter.ContentViewHolder>(ContentListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContentViewHolder(
            inflater.inflate(
                R.layout.content_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = getItem(position)
        holder.bind(content, context,
            favouritesClickListener = { logic.event(ContentListEvent.OnItemFavourited(it)) },
            itemClickListener = { logic.event(ContentListEvent.OnListItemClick(it)) })

    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(content: Content, context: Fragment, favouritesClickListener: (Int) -> Unit,
                      itemClickListener: (Content) -> Unit) {
            itemView.contentTitle.text = content.title
            itemView.contentOverview.text = content.overView
            itemView.contentVoteAverage.text = content.voteAverage.toString()
            setFavourite(context, content)

            Glide.with(context)
                .load(content.posterPath)
                .into(itemView.contentPosterThumbnail)

            itemView.contentFavourite.setOnClickListener {
                favouritesClickListener(adapterPosition)
                setFavourite(context, content)
            }
            itemView.setOnClickListener { itemClickListener(content) }
        }


        private fun setFavourite(context: Fragment, content: Content) {
            if (!content.isFavourite) {
                itemView.contentFavourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24px))
            } else {
                itemView.contentFavourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24px))
            }
        }
    }
}