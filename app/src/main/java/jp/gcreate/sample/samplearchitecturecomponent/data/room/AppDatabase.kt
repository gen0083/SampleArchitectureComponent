package jp.gcreate.sample.samplearchitecturecomponent.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData

/**
 * Copyright 2018 G-CREATE
 */
@Database(
    entities = [TestData::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testDataDao(): TestDataDao
}
