package com.tatianepinto.mvvm_kotlin_1valet.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.tatianepinto.mvvm_kotlin_1valet.model.Device

@Dao
interface DevicesDAO {

    @Query("SELECT * FROM Device")
    fun loadAll(): LiveData<List<Device>>

    @Query("SELECT * FROM Device WHERE id = :deviceId")
    fun loadDevice(deviceId: Long): Device
    @Query("SELECT * FROM Device WHERE id = :deviceId")
    fun loadLiveDevice(deviceId: Long): LiveData<Device>

    @Insert(onConflict = REPLACE)
    suspend fun insertDevice(device: Device): Long

    @Update(onConflict = REPLACE)
    fun updateDevice(device: Device)

    @Delete
    suspend fun deleteDevice(device: Device)
}