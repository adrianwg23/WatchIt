package com.example.adrianwong.watchit.contentlist

import androidx.recyclerview.widget.DiffUtil
import com.example.adrianwong.watchit.entities.Content
import com.example.adrianwong.watchit.entities.TvShow

class ContentListDiffUtilCallback : DiffUtil.ItemCallback<Content>() {

    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem.id == newItem.id
    }

}