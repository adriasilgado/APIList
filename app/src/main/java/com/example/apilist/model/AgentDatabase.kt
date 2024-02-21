package com.example.apilist.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = arrayOf(Data::class), version = 1)
@TypeConverters(Converters::class)
abstract class AgentDatabase:RoomDatabase() {
    abstract fun agentDao():AgentDao
}