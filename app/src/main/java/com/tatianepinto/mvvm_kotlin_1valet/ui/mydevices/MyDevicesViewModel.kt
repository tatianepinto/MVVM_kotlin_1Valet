package com.tatianepinto.mvvm_kotlin_1valet.ui.mydevices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyDevicesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is my devices Fragment"
    }
    val text: LiveData<String> = _text
}