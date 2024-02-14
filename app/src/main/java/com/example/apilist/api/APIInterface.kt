package com.example.apilist.api

import com.example.apilist.model.Agente
import com.example.apilist.model.Data
import com.example.apilist.model.ValorantAgentes
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIInterface {

    @GET("agents")
    suspend fun getCharacters(): Response<ValorantAgentes>

    @GET("agents/{uuid}")
    suspend fun getCharacter(@Path ("uuid") uuid:String):Response<Agente>

    companion object {
        val BASE_URL = "https://valorant-api.com/v1/"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }

}