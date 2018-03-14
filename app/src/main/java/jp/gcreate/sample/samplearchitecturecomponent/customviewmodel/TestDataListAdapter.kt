package jp.gcreate.sample.samplearchitecturecomponent.customviewmodel

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import jp.gcreate.sample.samplearchitecturecomponent.R
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData
import jp.gcreate.sample.samplearchitecturecomponent.databinding.ItemTestDataBinding

/**
 * Copyright 2018 G-CREATE
 */
class TestDataListAdapter : ListAdapter<TestData, BindingViewHolder<ItemTestDataBinding>>(
    object: DiffUtil.ItemCallback<TestData>() {
        override fun areItemsTheSame(oldItem: TestData, newItem: TestData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TestData, newItem: TestData): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemTestDataBinding> {
        return BindingViewHolder.createViewHolder(parent, R.layout.item_test_data)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemTestDataBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.data = item
    }
}
