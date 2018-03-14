package jp.gcreate.sample.samplearchitecturecomponent.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

/**
 * Copyright 2018 G-CREATE
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mutableThisInstance: MutableLiveData<String> = MutableLiveData()
    val viewModelInstance: LiveData<String> = mutableThisInstance

    init {
        mutableThisInstance.value = this.toString()
    }
}
