package com.example.sejtautomata_kotlin.onedimensional

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sejtautomata_kotlin.databinding.OnedCellgridFragmentCanvasBinding

class CellGridFragmentCanvas: Fragment() {
    private var _binding: OnedCellgridFragmentCanvasBinding? = null
    private val binding get() = _binding!!

    private val allGenerations = AllGenerations(100, 110)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OnedCellgridFragmentCanvasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.automata.allGenerations = allGenerations
        binding.automata.allGenerations?.apply {
            addEmptyGeneration()
            adjustHeight()
        }

        binding.addGenerationButton.setOnClickListener {
            generateGenerations()
        }
    }

    private fun generateGenerations() {
        var count = try {
            binding.generationCount.text.toString().toInt()
        }catch(e: Exception){
            0
        }
        if(count > 0) {
            for (i in 0..<count) {
                binding.automata.allGenerations?.generateNextGeneration()
            }
        }
        adjustHeight()

    }

    private fun adjustHeight(){
        val newHeight = binding.automata.allGenerations?.getGenerations()?.size?.times(binding.automata.currentCellSize)
        binding.automata.layoutParams = binding.automata.layoutParams.apply{
            height = newHeight!!
        }
        binding.automata.requestLayout()
        binding.automata.invalidate()
    }
}