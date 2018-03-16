package jp.gcreate.sample.samplearchitecturecomponent.data.repository

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData
import jp.gcreate.sample.samplearchitecturecomponent.data.room.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright 2018 G-CREATE
 */
@RunWith(AndroidJUnit4::class)
class TestRepositoryByRoomTest {
    private lateinit var sut: TestRepositoryByRoom
    private lateinit var db: AppDatabase

    @Before fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        sut = TestRepositoryByRoom(db.testDataDao())
    }

    @After fun tearDown() {
        db.close()
    }

    @Test fun insertDataTest() {
        val data = TestData(0, "test", "test data")
        sut.save(data)

        val actual = sut.load("test")
    }
}
