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

        binding.startButton.setOnClickListener {
            val action = OnedHomeFragmentDirections.actionOnedHomeFragmentToCellGridFragmentCanvas()
            findNavController().navigate(action)
        }

    }
}