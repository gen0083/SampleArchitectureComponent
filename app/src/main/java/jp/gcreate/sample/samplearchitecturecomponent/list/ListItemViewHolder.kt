package jp.gcreate.sample.samplearchitecturecomponent.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.gcreate.sample.samplearchitecturecomponent.R
import jp.gcreate.sample.samplearchitecturecomponent.data.ListItem
import jp.gcreate.sample.samplearchitecturecomponent.databinding.ItemListBinding

/**
 * Copyright 2018 G-CREATE
 */
class ListItemViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        binding.item = item
    }

    companion object {
        fun inflateWithParent(parent: ViewGroup): ListItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent, false)
            return ListItemViewHolder(binding)
        }
    }
}
