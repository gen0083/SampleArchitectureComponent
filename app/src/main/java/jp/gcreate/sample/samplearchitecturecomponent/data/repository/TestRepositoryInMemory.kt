package jp.gcreate.sample.samplearchitecturecomponent.data.repository

import jp.gcreate.sample.samplearchitecturecomponent.data.TestData

/**
 * Copyright 2018 G-CREATE
 */
class TestRepositoryInMemory : TestRepository{
    private val cache: MutableList<TestData> = mutableListOf()

    override fun save(data: TestData) {
        cache.add(data)
    }

    override fun load(name: String): TestData? {
        return cache.firstOrNull { it.name == name }
    }

    override fun loadAll(): List<TestData> {
        return cache
    }
}
