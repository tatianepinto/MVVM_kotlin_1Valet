package com.tatianepinto.mvvm_kotlin_1valet.repository

import com.tatianepinto.mvvm_kotlin_1valet.db.ConnectionService

class DevicesRepository constructor(private val connectionService: ConnectionService) {
    fun getAllDevices() = connectionService.getAllDevices()
}