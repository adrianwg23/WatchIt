package com.example.adrianwong.watchit.contentlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adrianwong.watchit.R
import com.example.adrianwong.watchit.common.load
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
                itemClickListener = { resultContent, view ->
                    logic.event(ContentListEvent.OnListItemClick(resultContent, view))
                })

    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(content: Content, context: Fragment, itemClickListener: (Content, View) -> Unit) {
            itemView.contentTitle.text = content.title
            itemView.contentOverview.text = content.overView
            itemView.contentVoteAverage.text = content.voteAverage.toString()
            itemView.contentPosterThumbnail.load(content.posterPath ?: "", context)
            itemView.setOnClickListener { itemClickListener(content, it) }
        }
    }
}