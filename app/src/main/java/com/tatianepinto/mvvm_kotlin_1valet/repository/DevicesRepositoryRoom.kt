package com.tatianepinto.mvvm_kotlin_1valet.repository

import android.content.Context
import com.tatianepinto.mvvm_kotlin_1valet.db.AppRoomDatabase
import com.tatianepinto.mvvm_kotlin_1valet.db.DevicesDAO
import com.tatianepinto.mvvm_kotlin_1valet.model.Device

class DevicesRepositoryRoom(context: Context) {

    private var db = AppRoomDatabase.getInstance(context)
    private var deviceDao: DevicesDAO = db.deviceDao()

    suspend fun addDevice(device: Device) : Long?  {
        val newId = deviceDao.insertDevice(device)
        device.id = newId
        return newId
    }

    fun createDevice() = Device()

    fun getLiveDevice(deviceId: Long) = deviceDao.loadLiveDevice(deviceId)

    fun updateDevice(device: Device) = deviceDao.updateDevice(device)

    suspend fun deleteDevice(device: Device) = deviceDao.deleteDevice(device)

    val allDevice = deviceDao.loadAll()
}