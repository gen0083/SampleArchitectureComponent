package jp.gcreate.sample.samplearchitecturecomponent.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import jp.gcreate.sample.samplearchitecturecomponent.R
import jp.gcreate.sample.samplearchitecturecomponent.data.ListItem

/**
 * Copyright 2018 G-CREATE
 */
class ListViewModel(application: Application): AndroidViewModel(application) {
    private val mutableLiveData: MutableLiveData<List<ListItem>> = MutableLiveData()
    val liveData: LiveData<List<ListItem>> = mutableLiveData
    private val localList: MutableList<ListItem> = mutableListOf()
    private var idCounter: Int = 0

    fun addItem() {
        val item = generateItem()
        localList.add(0, item)
        notifyList()
    }

    fun shuffle() {
        localList.shuffle()
        notifyList()
    }

    private fun notifyList() {
        // if setValue same instance, subscriber cannot received list after modified.
        mutableLiveData.value = localList.toList()
    }

    private fun generateItem(): ListItem {
        val id = idCounter++
        val (res, text) = when(id % 4) {
            0 -> R.drawable.ic_android to "android$id"
            1 -> R.drawable.ic_boat to "nice boat $id"
            2 -> R.drawable.ic_hand to "hand $id"
            else -> R.drawable.ic_photo to "photo $id"
        }
        return ListItem(id, res, text)
    }
}
