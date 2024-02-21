package com.example.apilist.model

import android.app.Application
import androidx.room.Room

class AgentApplication:Application() {
    companion object{
        lateinit var database: AgentDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AgentDatabase::class.java,"AgentDatabase").build()
    }
}