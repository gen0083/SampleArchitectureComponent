package jp.gcreate.sample.samplearchitecturecomponent

import android.app.Application
import android.arch.persistence.room.Room
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepository
import jp.gcreate.sample.samplearchitecturecomponent.data.repository.TestRepositoryByRoom
import jp.gcreate.sample.samplearchitecturecomponent.data.room.AppDatabase
import jp.gcreate.sample.samplearchitecturecomponent.ui.customviewmodel.CustomViewModelFactory
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */
class App : Application() {
    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "test-db")
            .allowMainThreadQueries()
            .build()
    }
    private val testRepository: TestRepository by lazy { TestRepositoryByRoom(appDatabase.testDataDao()) }
    val customViewModelFactory: CustomViewModelFactory by lazy { CustomViewModelFactory(testRepository) }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
