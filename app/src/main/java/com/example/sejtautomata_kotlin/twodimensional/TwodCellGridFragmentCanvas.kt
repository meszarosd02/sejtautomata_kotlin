package com.example.sejtautomata_kotlin.twodimensional

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sejtautomata_kotlin.databinding.TwodCellgridFragmentCanvasBinding

class TwodCellGridFragmentCanvas: Fragment() {
    private var _binding: TwodCellgridFragmentCanvasBinding? = null
    private val binding get() = _binding!!

    private var rows = 6
    private var cols = 6

    private var allGenerations: AllGenerations = AllGenerations(rows, cols)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TwodCellgridFragmentCanvasBinding.inflate(inflater, container, false)
        allGenerations.addRule(Rule(Rule.COMPARISON.LESS, 2, Rule.STARTSTATE.ACTIVE, false))
        allGenerations.addRule(Rule(Rule.COMPARISON.EQUAL, 2, Rule.STARTSTATE.ACTIVE, true))
        allGenerations.addRule(Rule(Rule.COMPARISON.EQUAL, 3, Rule.STARTSTATE.ANY, true))
        allGenerations.addRule(Rule(Rule.COMPARISON.GREATER, 3, Rule.STARTSTATE.ACTIVE, false))
        allGenerations.addGeneration(Generation(rows, cols))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.canvas.setGeneration(allGenerations)

        binding.neighbors.setOnClickListener {

        }

        binding.nextGen.setOnClickListener {
            binding.canvas.generateNextGeneration()
        }
    }
}