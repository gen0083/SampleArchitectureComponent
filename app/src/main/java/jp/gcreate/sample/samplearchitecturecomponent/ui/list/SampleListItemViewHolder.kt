package jp.gcreate.sample.samplearchitecturecomponent.ui.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.gcreate.sample.samplearchitecturecomponent.R
import jp.gcreate.sample.samplearchitecturecomponent.data.SampleListItem
import jp.gcreate.sample.samplearchitecturecomponent.databinding.ItemListBinding

/**
 * Copyright 2018 G-CREATE
 */
class SampleListItemViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SampleListItem) {
        binding.item = item
    }

    companion object {
        fun inflateWithParent(parent: ViewGroup): SampleListItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent, false)
            return SampleListItemViewHolder(binding)
        }
    }
}
