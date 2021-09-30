package com.tatianepinto.mvvm_kotlin_1valet.db

import com.tatianepinto.mvvm_kotlin_1valet.model.Device
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ConnectionService {
    @GET("devices.json")
    fun getAllDevices() : Call<List<Device>>

    companion object {

        var connectionService: ConnectionService? = null

        fun getInstance() : ConnectionService {

            if (connectionService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://tatianepintoApi.com/") //fake ;)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                connectionService = retrofit.create(ConnectionService::class.java)
            }
            return connectionService!!
        }
    }
}