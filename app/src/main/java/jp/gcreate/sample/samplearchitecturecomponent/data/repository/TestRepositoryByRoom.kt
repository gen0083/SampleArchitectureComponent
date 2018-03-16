package jp.gcreate.sample.samplearchitecturecomponent.data.repository

import android.arch.lifecycle.LiveData
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData
import jp.gcreate.sample.samplearchitecturecomponent.data.room.TestDataDao

/**
 * Copyright 2018 G-CREATE
 */
class TestRepositoryByRoom(private val dao: TestDataDao): TestRepository {

    override fun watch(): LiveData<List<TestData>> {
        return dao.watch()
    }

    override fun save(data: TestData) {
        dao.insertTestData(data)
    }

    override fun load(name: String): TestData? {
        return dao.load(name)
    }

    override fun loadAll(): List<TestData> {
        return dao.loadAll()
    }
}
