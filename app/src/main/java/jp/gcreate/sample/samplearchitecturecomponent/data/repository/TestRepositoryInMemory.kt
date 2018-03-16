package jp.gcreate.sample.samplearchitecturecomponent.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData

/**
 * Copyright 2018 G-CREATE
 */
class TestRepositoryInMemory : TestRepository{
    private val cache: MutableList<TestData> = mutableListOf()
    private val mutableLiveData: MutableLiveData<List<TestData>> = MutableLiveData()

    override fun save(data: TestData) {
        cache.add(data)
        mutableLiveData.value = cache
    }

    override fun load(name: String): TestData? {
        return cache.firstOrNull { it.name == name }
    }

    override fun loadAll(): List<TestData> {
        mutableLiveData.value = cache
        return cache
    }

    override fun monitorAll(): LiveData<List<TestData>> {
        return mutableLiveData
    }
}
