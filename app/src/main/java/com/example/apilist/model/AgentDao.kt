package com.example.apilist.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AgentDao {
    @Query("SELECT * FROM AgentEntity")
    suspend fun getAllAgents():MutableList<Data>
    @Query("SELECT * FROM AgentEntity WHERE uuid = :agentId")
    suspend fun getAgentByID(agentId: String):MutableList<Data>
    @Insert
    suspend fun addAgent(agent:Data)
    @Delete
    suspend fun deleteAgent(agent:Data)
}