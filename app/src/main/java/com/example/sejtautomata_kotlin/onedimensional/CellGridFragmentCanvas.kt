package com.example.sejtautomata_kotlin.onedimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sejtautomata_kotlin.databinding.OnedCellgridFragmentCanvasBinding

class CellGridFragmentCanvas: Fragment() {
    private var _binding: OnedCellgridFragmentCanvasBinding? = null
    val binding get() = _binding!!

    var rowSize = 100
    var ruleSet = 110

    private val allGenerations = AllGenerations(rowSize, ruleSet)

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
            refreshCanvas()
            binding.toolbar.title = "Rule: ${this.getRuleSet()}; Size: ${this.getSize()}"
        }

        binding.addGenerationButton.setOnClickListener {
            generateGenerations()
        }

        binding.modify.setOnClickListener {
            ModifyAutomatonDialogFragment().show(childFragmentManager, "ModifyAutomatonDialogFragment")
        }

        binding.setGenZero.setOnClickListener {
            SetGenZeroDialogFragment().show(childFragmentManager, "SetGenZeroDialogFragment")
        }
    }

    fun newRandomGenerationZero() {
        binding.automata.allGenerations = AllGenerations(rowSize, ruleSet).apply{
            randomGenerationZero()
        }
        refreshCanvas()
    }

    fun newLastCellGenerationZero() {
        binding.automata.allGenerations = AllGenerations(rowSize, ruleSet).apply{
            lastCellGenerationZero()
        }
        refreshCanvas()
    }

    fun newNthCellGenerationZero(n: Int){
        binding.automata.allGenerations = AllGenerations(rowSize, ruleSet).apply{
            nthCellGenerationZero(n)
        }
        refreshCanvas()
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
        refreshCanvas()

    }

    private fun refreshCanvas(){
        binding.automata.requestLayout()
        binding.automata.invalidate()
    }
}