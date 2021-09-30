package com.tatianepinto.mvvm_kotlin_1valet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tatianepinto.mvvm_kotlin_1valet.repository.DevicesRepository

class DevicesViewModelFactory constructor(private val repository: DevicesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DevicesViewModel::class.java)) {
            DevicesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}
