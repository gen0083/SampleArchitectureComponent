package jp.gcreate.sample.samplearchitecturecomponent.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Copyright 2018 G-CREATE
 */
@Entity(tableName = RoomConstants.TEST_DATA_TABLE_NAME)
data class TestData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var value: String
)
