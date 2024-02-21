package com.example.apilist.api

import com.example.apilist.model.AgentApplication
import com.example.apilist.model.Data

class Repository {

    val apiInterface = APIInterface.create()
    val daoInterfase = AgentApplication.database.agentDao()

    suspend fun getAllCharacters() = apiInterface.getCharacters()

    suspend fun getCharacter(uuid:String) = apiInterface.getCharacter(uuid)

    suspend fun saveAsFavourite(agent:Data) = daoInterfase.addAgent(agent)
    suspend fun deleteFavourite(agent:Data) = daoInterfase.deleteAgent(agent)
    suspend fun isFavourite(agent:Data) = daoInterfase.getAgentByID(agent.uuid).isNotEmpty()
    suspend fun getFavourites() = daoInterfase.getAllAgents()
}