package jp.gcreate.sample.samplearchitecturecomponent

import android.app.Application
import timber.log.Timber

/**
 * Copyright 2018 G-CREATE
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
