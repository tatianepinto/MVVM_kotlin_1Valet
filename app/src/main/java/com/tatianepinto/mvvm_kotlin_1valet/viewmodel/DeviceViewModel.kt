package com.tatianepinto.mvvm_kotlin_1valet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatianepinto.mvvm_kotlin_1valet.model.Device
import com.tatianepinto.mvvm_kotlin_1valet.repository.DevicesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DevicesViewModel constructor(private val repository: DevicesRepository)  : ViewModel(){

    val devicesList = MutableLiveData<List<Device>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllDevices() {

        val response = repository.getAllDevices()
        response.enqueue(object : Callback<List<Device>> {
            override fun onResponse(call: Call<List<Device>>, response: Response<List<Device>>) {
                devicesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Device>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}