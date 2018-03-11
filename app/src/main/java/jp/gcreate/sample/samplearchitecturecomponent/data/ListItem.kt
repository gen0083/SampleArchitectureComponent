package jp.gcreate.sample.samplearchitecturecomponent.data

import android.support.annotation.DrawableRes

/**
 * Copyright 2018 G-CREATE
 */
data class ListItem(val id: Int,
                    @DrawableRes val image: Int,
                    val text: String)
