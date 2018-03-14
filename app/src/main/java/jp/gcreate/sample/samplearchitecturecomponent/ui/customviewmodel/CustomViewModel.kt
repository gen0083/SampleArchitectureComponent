package jp.gcreate.sample.samplearchitecturecomponent.ui.customviewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepository
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */

private const val EMPTY_MESSAGE = "empty not allowed"

class CustomViewModel(private val repository: TestRepository): ViewModel() {
    private val dispatcherTestData: MutableLiveData<List<TestData>> = MutableLiveData()
    val testData: LiveData<List<TestData>> = dispatcherTestData
    private val dispatcherNameError: MutableLiveData<String?> = MutableLiveData()
    val nameError: LiveData<String?> = dispatcherNameError
    private val dispatcherValueError: MutableLiveData<String?> = MutableLiveData()
    val valueError: LiveData<String?> = dispatcherValueError

    private val testObserver = Observer<List<TestData>> {
        Timber.v("observer value changed: item count=${it?.size}")
    }

    init {
        dispatcherTestData.value = repository.loadAll()
        dispatcherTestData.observeForever(testObserver)
    }

    fun addData(name: String, value: String): Boolean {
        dispatcherNameError.value = if (name.isEmpty()) EMPTY_MESSAGE else null
        dispatcherValueError.value = if (value.isEmpty()) EMPTY_MESSAGE else null
        if (name.isEmpty() || value.isEmpty()) return false

        val data = TestData(name, value)
        repository.save(data)
        dispatcherTestData.value = repository.loadAll()
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Timber.v("onCleared")
        dispatcherTestData.removeObserver(testObserver)
    }
}
