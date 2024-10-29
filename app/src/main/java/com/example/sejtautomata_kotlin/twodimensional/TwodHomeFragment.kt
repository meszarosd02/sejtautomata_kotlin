package com.example.sejtautomata_kotlin.twodimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sejtautomata_kotlin.databinding.TwodHomeFragmentBinding

class TwodHomeFragment: Fragment() {
    private var _binding: TwodHomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TwodHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            val action = TwodHomeFragmentDirections.actionTwodHomeFragmentToTwodCellGridFragmentCanvas()
            findNavController().navigate(action)
        }
    }
}