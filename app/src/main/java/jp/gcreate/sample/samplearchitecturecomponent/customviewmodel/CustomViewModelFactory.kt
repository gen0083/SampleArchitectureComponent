package jp.gcreate.sample.samplearchitecturecomponent.customviewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepository
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */
class CustomViewModelFactory(private val repository: TestRepository) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Timber.d("create ViewModel: modelClass=$modelClass")
        if (modelClass.isAssignableFrom(CustomViewModel::class.java)) {
            return CustomViewModel(repository) as T
        } else {
            throw RuntimeException("cannot create instance of $modelClass")
        }
    }
}
