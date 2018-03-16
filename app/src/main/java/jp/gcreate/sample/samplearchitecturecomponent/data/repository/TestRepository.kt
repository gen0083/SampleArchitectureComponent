package jp.gcreate.sample.samplearchitecturecomponent.data.repository

import android.arch.lifecycle.LiveData
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData

/**
 * Copyright 2018 G-CREATE
 */
interface TestRepository {
    fun save(data: TestData)
    fun load(name: String): TestData?
    fun loadAll(): List<TestData>
    fun watch(): LiveData<List<TestData>>
}
