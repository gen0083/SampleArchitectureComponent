package jp.gcreate.sample.samplearchitecturecomponent.ui.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import jp.gcreate.sample.samplearchitecturecomponent.R
import jp.gcreate.sample.samplearchitecturecomponent.data.SampleListItem
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */
class SampleListViewModel(application: Application): AndroidViewModel(application) {
    private val mutableLiveData: MutableLiveData<List<SampleListItem>> = MutableLiveData()
    val liveData: LiveData<List<SampleListItem>> = mutableLiveData
    private val localList: MutableList<SampleListItem> = mutableListOf()
    private var idCounter: Int = 0
    private val testObserver = Observer<List<SampleListItem>> {
        Timber.d("mutable live data observe: list size=${it?.size} $it")
    }

    init {
        mutableLiveData.observeForever(testObserver)
        Timber.d("test observer added")
    }

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
        // if setValue same instance, ListAdapter dose not dispatch to update item if it modified
        mutableLiveData.value = localList.toList()
    }

    private fun generateItem(): SampleListItem {
        val id = idCounter++
        val (res, text) = when(id % 4) {
            0 -> R.drawable.ic_android to "android$id"
            1 -> R.drawable.ic_boat to "nice boat $id"
            2 -> R.drawable.ic_hand to "hand $id"
            else -> R.drawable.ic_photo to "photo $id"
        }
        return SampleListItem(id, res, text)
    }

    override fun onCleared() {
        super.onCleared()
        mutableLiveData.removeObserver(testObserver)
        Timber.d("test observer removed")
    }
}
