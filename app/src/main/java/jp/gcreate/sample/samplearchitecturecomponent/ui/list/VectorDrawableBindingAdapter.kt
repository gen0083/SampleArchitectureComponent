package jp.gcreate.sample.samplearchitecturecomponent.ui.list

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.support.v7.widget.AppCompatImageView

/**
 * Copyright 2018 G-CREATE
 */

@BindingMethods(
    BindingMethod(type = AppCompatImageView::class,
                  attribute = "srcCompat",
                  method = "setImageResource")
)
class VectorDrawableBindingAdapter
