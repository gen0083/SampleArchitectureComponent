package jp.gcreate.sample.samplearchitecturecomponent.ui.list

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import jp.gcreate.sample.samplearchitecturecomponent.data.SampleListItem
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */
class SampleListAdapter : ListAdapter<SampleListItem, SampleListItemViewHolder>(
    object: DiffUtil.ItemCallback<SampleListItem>() {
        override fun areItemsTheSame(oldItem: SampleListItem, newItem: SampleListItem): Boolean {
            val areItemsSame = oldItem.id == newItem.id
            Timber.v("are items the same=$areItemsSame old:$oldItem new:$newItem")
            return areItemsSame
        }

        override fun areContentsTheSame(oldItem: SampleListItem, newItem: SampleListItem): Boolean {
            val areContentsSame = oldItem == newItem
            Timber.v("are items the same=$areContentsSame old:$oldItem new:$newItem")
            return areContentsSame
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleListItemViewHolder {
        Timber.d("onCreateViewHolder $parent $viewType")
        return SampleListItemViewHolder.inflateWithParent(parent)
    }

    override fun onBindViewHolder(holder: SampleListItemViewHolder, position: Int) {
        Timber.d("onBindViewHolder $holder $position")
        val item = getItem(position)
        holder.bind(item)
    }
}
