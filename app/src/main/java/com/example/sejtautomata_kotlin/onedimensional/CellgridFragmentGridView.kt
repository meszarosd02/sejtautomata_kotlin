package com.example.sejtautomata_kotlin.onedimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sejtautomata_kotlin.R
import com.example.sejtautomata_kotlin.databinding.OnedCellgridFragmentGridviewBinding

class CellgridFragmentGridView: Fragment() {
    private var _binding: OnedCellgridFragmentGridviewBinding? = null
    private val binding get() = _binding!!

    private var allGeneration = CellularAutomata(50, 110)

    private var allCells: ArrayList<Cell> = ArrayList()

    private var adapter: CellGridAdapterGridView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGeneration.randomGenerationZero()
        allCells = allGeneration.getAllCells()
        adapter = CellGridAdapterGridView(requireContext(), R.layout.oned_cell_item, allCells, allGeneration.getSize())
        _binding = OnedCellgridFragmentGridviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cellGrid.numColumns = allGeneration.getSize()
        binding.cellGrid.adapter = adapter

        binding.addGenerationButton.setOnClickListener{
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
                allGeneration.generateNextGeneration()
            }
        }
        allCells = allGeneration.getAllCells()
        adapter = CellGridAdapterGridView(requireContext(), R.layout.oned_cell_item, allCells, allGeneration.getSize())
        binding.cellGrid.adapter = adapter
    }
}