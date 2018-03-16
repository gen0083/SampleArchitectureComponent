package jp.gcreate.sample.samplearchitecturecomponent.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import jp.gcreate.sample.samplearchitecturecomponent.data.TestData
import jp.gcreate.sample.samplearchitecturecomponent.data.room.AppDatabase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright 2018 G-CREATE
 */
@RunWith(AndroidJUnit4::class)
class TestRepositoryByRoomTest {
    private lateinit var sut: TestRepositoryByRoom
    private lateinit var db: AppDatabase
    @get:Rule var executorRule = InstantTaskExecutorRule()

    @Before fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build()
        sut = TestRepositoryByRoom(db.testDataDao())
    }

    @After fun tearDown() {
        db.close()
    }

    @Test fun insertDataTest() {
        val data = TestData(0, "test", "test data")
        sut.save(data)

        val actual = sut.load("test")
        assertThat(actual, `is`(notNullValue()))
        // id is not same, caused by auto increment
        assertThat(actual!!.name, `is`(data.name))
    }

    @Test fun insertDataTwice() {
        var list = listOf<TestData>()
        val observer = Observer<List<TestData>> {
            it?.let { list = it }
        }

        sut.watch().observeForever(observer)

        sut.save(TestData(0, "test1", "test1 data"))
        sut.save(TestData(0, "test2", "test2 data"))

        assertThat(sut.load("test1"), `is`(TestData(1, "test1", "test1 data")))
        assertThat(sut.load("test2"), `is`(TestData(2, "test2", "test2 data")))

        assertThat(list.size, `is`(2))
        assertThat(sut.loadAll().size, `is`(2))
        assertThat(sut.watch().value, `is`(nullValue()))

        sut.watch().removeObserver(observer)
    }
}
