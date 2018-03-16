package jp.gcreate.sample.samplearchitecturecomponent.ui.customviewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepository
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */

private const val EMPTY_MESSAGE = "empty not allowed"

class CustomViewModel(private val repository: TestRepository): ViewModel() {
    val testData: LiveData<List<TestData>> = repository.monitorAll()
    private val dispatcherNameError: MutableLiveData<String?> = MutableLiveData()
    val nameError: LiveData<String?> = dispatcherNameError
    private val dispatcherValueError: MutableLiveData<String?> = MutableLiveData()
    val valueError: LiveData<String?> = dispatcherValueError

    fun addData(name: String, value: String): Boolean {
        dispatcherNameError.value = if (name.isEmpty()) EMPTY_MESSAGE else null
        dispatcherValueError.value = if (value.isEmpty()) EMPTY_MESSAGE else null
        if (name.isEmpty() || value.isEmpty()) return false

        val data = TestData(0, name, value)
        repository.save(data)
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Timber.v("onCleared")
    }
}
