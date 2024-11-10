package com.example.sejtautomata_kotlin.onedimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sejtautomata_kotlin.databinding.OnedHomeFragmentBinding

class OnedHomeFragment: Fragment() {
    private var _binding: OnedHomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OnedHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.canvasButton.setOnClickListener {
            val action = OnedHomeFragmentDirections.actionOnedHomeFragmentToCellGridFragmentCanvas()
            findNavController().navigate(action)
        }

        binding.gridviewButton.setOnClickListener {
            val action = OnedHomeFragmentDirections.actionOnedHomeFragmentToCellgridFragmentGridView()
            findNavController().navigate(action)
        }

        binding.recyclerviewButton.setOnClickListener {
            val action = OnedHomeFragmentDirections.actionOnedHomeFragmentToCellgridFragmentRecyclerView()
            findNavController().navigate(action)
        }*/

    }
}