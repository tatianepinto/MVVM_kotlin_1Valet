package com.tatianepinto.mvvm_kotlin_1valet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tatianepinto.mvvm_kotlin_1valet.R
import com.tatianepinto.mvvm_kotlin_1valet.model.Device
import java.util.*
import kotlin.collections.ArrayList

class DevicesRecyclerViewAdapter(val clickListener: (showDevice:String) -> Unit) :
    RecyclerView.Adapter<DevicesRecyclerViewAdapter.DeviceViewHolder>(), Filterable {

//    private var devicesData = mutableListOf<Device>()
    private var devicesData = emptyList<Device>()
    private var deviceFilterList = devicesData

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listName: TextView = itemView.findViewById(R.id.tx_name)
        val listStatus: TextView = itemView.findViewById(R.id.tx_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.device_view_holder, parent, false)
        return DeviceViewHolder(view)
    }

    override fun getItemCount() = deviceFilterList.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.listName.text =  deviceFilterList[position].name
        holder.listStatus.text = "Status: Available"

        //Long click delete and one click go to edit page
        holder.itemView.setOnClickListener {
            clickListener(deviceFilterList[position].id.toString())
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                deviceFilterList = if (charSearch.isEmpty()) {
                    devicesData
                } else {
                    val resultList = ArrayList<Device>()
                    for (row in deviceFilterList) {
                        if (row.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = deviceFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                deviceFilterList = results?.values as ArrayList<Device>
                notifyDataSetChanged()
            }

        }
    }

    internal fun setDeviceList(devices: List<Device>) {
        this.devicesData = devices.toMutableList()
        deviceFilterList = devicesData
        notifyDataSetChanged()
    }
}
