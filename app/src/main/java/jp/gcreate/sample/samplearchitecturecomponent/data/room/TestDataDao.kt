package jp.gcreate.sample.samplearchitecturecomponent.data.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import jp.gcreate.sample.samplearchitecturecomponent.data.RoomConstants
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData

/**
 * Copyright 2018 G-CREATE
 */
@Dao
interface TestDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTestData(testData: TestData)

    @Query("select * from ${RoomConstants.TEST_DATA_TABLE_NAME}")
    fun loadAll(): LiveData<List<TestData>>

    @Query("select * from ${RoomConstants.TEST_DATA_TABLE_NAME} where name = :name limit 1")
    fun load(name: String): TestData
}
