package com.tatianepinto.mvvm_kotlin_1valet.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatianepinto.mvvm_kotlin_1valet.MainActivity
import com.tatianepinto.mvvm_kotlin_1valet.R
import com.tatianepinto.mvvm_kotlin_1valet.adapter.DevicesRecyclerViewAdapter
import com.tatianepinto.mvvm_kotlin_1valet.databinding.FragmentHomeBinding
import com.tatianepinto.mvvm_kotlin_1valet.ui.DeviceDescriptionActivity
import com.tatianepinto.mvvm_kotlin_1valet.viewmodel.DeviceViewModelRoom
import com.tatianepinto.mvvm_kotlin_1valet.viewmodel.DevicesViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.app_bar_main.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var listsRecyclerView: RecyclerView
    private lateinit var deviceViewModelRoom: DeviceViewModelRoom
    private lateinit var searchView: SearchView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        listsRecyclerView = _binding!!.devicesRecyclerview
        listsRecyclerView.layoutManager = LinearLayoutManager(context)
        listsRecyclerView.adapter = DevicesRecyclerViewAdapter { detailDevice : String -> goToDeviceDescriptionActivity(detailDevice) }

        setupViewModel()
        createFakeRegisters() //To show some devices
        populateRecyclerView()

//        searchView = _binding!!.devicesRecyclerview.findViewById(R.id.search_bar)
//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                deviceViewModelRoom.
//                listsRecyclerView.adapter.get .filter.filter(newText)
//                return false
//            }
//
//        })

        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        searchView = menu.findItem(R.id.search_bar).actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                (listsRecyclerView.adapter as DevicesRecyclerViewAdapter).filter.filter(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        goToDeviceDescriptionActivity("detailDevice")
        return super.onOptionsItemSelected(item)
    }

    private fun goToDeviceDescriptionActivity(detailDevice: String?) {
        val intent = Intent(activity, DeviceDescriptionActivity::class.java)
        detailDevice.let { intent.putExtra("deviceId", it) }
        startActivity(intent)
    }

    private fun setupViewModel() {
        deviceViewModelRoom = ViewModelProvider(this).get(DeviceViewModelRoom::class.java)
    }
    private fun populateRecyclerView(){
        deviceViewModelRoom.getAllDevice().observe(viewLifecycleOwner, androidx.lifecycle.Observer { devices ->
            // Update the cached copy of the words in the adapter.
            devices?.let { (listsRecyclerView.adapter as DevicesRecyclerViewAdapter).setDeviceList(it) }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createFakeRegisters(){
        //Insert Case
        val thread = Thread {
            deviceViewModelRoom.addNewDevice("Sansung Galaxy",
                "Sensor",
                20.00,
                "USD",
                false,
                "",
                "Test Sensor",
                "")
            deviceViewModelRoom.addNewDevice("Sansung Galaxy",
                "Thermostat",
                25.00,
                "USD",
                false,
                "",
                "Test Thermostat",
                "")

            //fetch Records
            deviceViewModelRoom.getAllDevice().value?.forEach()
            {
                Log.i("Fetch Records", "Id:  : ${it.id}")
                Log.i("Fetch Records", "Name:  : ${it.name}")
            }
        }
        deviceViewModelRoom.getAllDevice().observe(viewLifecycleOwner, androidx.lifecycle.Observer { devices ->
            if(devices.isEmpty()) thread.start()
        })
    }
}