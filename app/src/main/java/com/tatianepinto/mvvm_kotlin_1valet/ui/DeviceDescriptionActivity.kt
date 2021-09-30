package com.tatianepinto.mvvm_kotlin_1valet.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tatianepinto.mvvm_kotlin_1valet.R
import com.tatianepinto.mvvm_kotlin_1valet.viewmodel.DeviceViewModelRoom
import kotlinx.android.synthetic.main.activity_device_description.*
import java.text.SimpleDateFormat
import java.util.*

class DeviceDescriptionActivity : AppCompatActivity() {

    private lateinit var deviceViewModelRoom: DeviceViewModelRoom
    private lateinit var deviceView: DeviceViewModelRoom.DeviceDetailsView
    private var deviceId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_description);

        title = getString(R.string.title_detail)

        setupViewModel()
        getIntentData()
    }

    private fun getIntentData() {
        val deviceId = intent.getStringExtra("deviceId")
        deviceId?.let {
            deviceViewModelRoom.getDevice(it.toLong())?.observe(
                this, Observer { deviceDetailsView ->
                    deviceDetailsView?.let {
                        deviceView = deviceDetailsView
                        setFields()
                    }
                })
        }
    }

    private fun setFields() {
        deviceView?.let { deviceView ->
            textName.text = "Name from DB: "+deviceView.name
            deviceId = deviceView.id!!
        }
    }

    private fun setupViewModel() {
        deviceViewModelRoom = ViewModelProvider(this).get(DeviceViewModelRoom::class.java)
    }
}