package jp.gcreate.sample.samplearchitecturecomponent

import android.app.Application
import jp.gcreate.sample.samplearchitecturecomponent.customviewmodel.CustomViewModelFactory
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepository
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepositoryInMemory
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */
class App : Application() {
    private val testRepository: TestRepository = TestRepositoryInMemory()
    val customViewModelFactory = CustomViewModelFactory(testRepository)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
