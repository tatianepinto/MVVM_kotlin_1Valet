package com.tatianepinto.mvvm_kotlin_1valet.ui.mydevices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tatianepinto.mvvm_kotlin_1valet.databinding.FragmentMyDevicesBinding

class MyDevicesFragment : Fragment() {

    private lateinit var myDevicesViewModel: MyDevicesViewModel
    private var _binding: FragmentMyDevicesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myDevicesViewModel =
            ViewModelProvider(this).get(MyDevicesViewModel::class.java)

        _binding = FragmentMyDevicesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDevices
        myDevicesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}