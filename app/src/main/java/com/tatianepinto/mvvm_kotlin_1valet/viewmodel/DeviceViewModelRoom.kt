package com.tatianepinto.mvvm_kotlin_1valet.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.tatianepinto.mvvm_kotlin_1valet.model.Device
import com.tatianepinto.mvvm_kotlin_1valet.repository.DevicesRepositoryRoom
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class DeviceViewModelRoom(application: Application) :
    AndroidViewModel(application) {
    private val TAG = "DevicesViewModelRoomLog"

    private var deviceRepositoryRoom: DevicesRepositoryRoom = DevicesRepositoryRoom(this.getApplication())
    private var deviceDetailsView: LiveData<DeviceDetailsView>? = null

    data class DeviceDetailsView(
        var id: Long? = null,
        var name: String = "",
        var type: String = "",
        var price: Double = 0.0,
        var currency: String = "",
        var isFavorite: Boolean = false,
        var imageUrl: String = "",
        var title: String = "",
        var description: String = ""
    )

    fun addNewDevice(name: String, type: String, price: Double, currency: String, isFavorite: Boolean, imageUrl: String, title: String, description: String) {

        val newDevice = deviceRepositoryRoom.createDevice()
        newDevice.name = name
        newDevice.type = type
        newDevice.price = price
        newDevice.currency = currency
        newDevice.isFavorite = isFavorite
        newDevice.imageUrl = imageUrl
        newDevice.title = title
        newDevice.description = description

        try {
            viewModelScope.launch {
                val newId = deviceRepositoryRoom.addDevice(newDevice)
                Log.i(TAG, "New device $newId added to the database.")
            }
        } catch (e:Exception){
            Log.i("ErrorApp", "Error:" + e)
        }
    }

    fun updateDevice(studentView: DeviceDetailsView) {
        GlobalScope.launch {
            studentView.let {
                val device = getDeviceClass(it)
                device?.let { deviceRepositoryRoom.updateDevice(device) }
            }
        }
    }

    fun deleteDevice(studentView: DeviceDetailsView) {
        GlobalScope.launch {
            studentView.let {
                val device = getDeviceClass(it)
                device?.let { deviceRepositoryRoom.deleteDevice(device) }
            }
        }
    }

    fun getAllDevice() = deviceRepositoryRoom.allDevice

    fun getDevice (deviceId: Long) : LiveData<DeviceDetailsView>? {
        if (deviceDetailsView == null) {
            val deviceLiveData =  deviceRepositoryRoom.getLiveDevice(deviceId)
            deviceDetailsView = Transformations.map(deviceLiveData) {
                    repositoryDevice ->
                deviceView(repositoryDevice)
            }
        }
        return deviceDetailsView
    }

    private fun deviceView(device: Device): DeviceDetailsView {
        return DeviceDetailsView(device.id, device.name, device.type, device.price, device.currency, device.isFavorite, device.imageUrl, device.title, device.description)
    }

    private fun getDeviceClass(device: DeviceDetailsView): Device {
        return Device(device.id, device.name, device.type, device.price, device.currency, device.isFavorite, device.imageUrl, device.title, device.description)
    }
}