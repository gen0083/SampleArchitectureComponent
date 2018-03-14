package jp.gcreate.sample.samplearchitecturecomponent.ui.list

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import jp.gcreate.sample.samplearchitecturecomponent.data.SampleListItem

/**
 * Copyright 2018 G-CREATE
 */
class SampleListAdapter : ListAdapter<SampleListItem, SampleListItemViewHolder>(
    object: DiffUtil.ItemCallback<SampleListItem>() {
        override fun areItemsTheSame(oldItem: SampleListItem, newItem: SampleListItem): Boolean
            = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SampleListItem, newItem: SampleListItem): Boolean
            = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleListItemViewHolder
        = SampleListItemViewHolder.inflateWithParent(parent)

    override fun onBindViewHolder(holder: SampleListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
