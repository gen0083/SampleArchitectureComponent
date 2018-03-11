package jp.gcreate.sample.samplearchitecturecomponent.list

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import jp.gcreate.sample.samplearchitecturecomponent.data.ListItem

/**
 * Copyright 2018 G-CREATE
 */
class SampleListAdapter : ListAdapter<ListItem, ListItemViewHolder>(
    object: DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean
            = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean
            = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder
        = ListItemViewHolder.inflateWithParent(parent)

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
