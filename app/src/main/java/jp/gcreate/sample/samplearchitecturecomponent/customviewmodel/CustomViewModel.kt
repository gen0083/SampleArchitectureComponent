package jp.gcreate.sample.samplearchitecturecomponent.customviewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepository
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */
class CustomViewModel(private val repository: TestRepository): ViewModel() {
    private val dispatcherTestData: MutableLiveData<List<TestData>> = MutableLiveData()
    val testData: LiveData<List<TestData>> = dispatcherTestData

    init {
        dispatcherTestData.value = repository.loadAll()
    }

    fun addData(name: String, value: String): Boolean {
        if (name.isEmpty() || value.isEmpty()) return false

        val data = TestData(name, value)
        repository.save(data)
        dispatcherTestData.value = repository.loadAll()
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Timber.v("onCleared")
    }
}
