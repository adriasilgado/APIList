package com.example.apilist.api

class Repository {

    val apiInterface = APIInterface.create()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
}