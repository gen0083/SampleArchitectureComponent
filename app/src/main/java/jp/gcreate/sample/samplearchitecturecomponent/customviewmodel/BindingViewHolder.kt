package jp.gcreate.sample.samplearchitecturecomponent.customviewmodel

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Copyright 2018 G-CREATE
 */
class BindingViewHolder<out T: ViewDataBinding>(val binding: T)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        inline fun <reified T: ViewDataBinding> createViewHolder(parent: ViewGroup, @LayoutRes layoutRes: Int): BindingViewHolder<T> {
            val binding = DataBindingUtil.inflate<T>(LayoutInflater.from(parent.context),
                                                     layoutRes, parent, false)
            return BindingViewHolder(binding)
        }
    }
}
