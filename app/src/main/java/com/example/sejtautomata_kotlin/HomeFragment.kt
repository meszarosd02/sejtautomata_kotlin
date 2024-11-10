package com.example.sejtautomata_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sejtautomata_kotlin.databinding.HomeFragmentBinding

class HomeFragment: Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.oneDimension.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCellGridFragmentCanvas()
            findNavController().navigate(action)
        }
        binding.twoDimension.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToTwodCellGridFragmentCanvas()
            findNavController().navigate(action)
        }
    }
}