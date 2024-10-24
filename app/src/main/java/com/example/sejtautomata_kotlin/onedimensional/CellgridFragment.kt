package com.example.sejtautomata_kotlin.onedimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sejtautomata_kotlin.R
import com.example.sejtautomata_kotlin.databinding.OnedCellgridFragmentBinding

class CellgridFragment: Fragment() {
    private var _binding: OnedCellgridFragmentBinding? = null
    private val binding get() = _binding!!

    private var allGeneration = AllGenerations(20, 1)

    private var adapter: CellGridAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGeneration.addEmptyGeneration()
        for(i in 0..10){
            allGeneration.generateNextGeneration()
        }
        adapter = CellGridAdapter(requireContext(), R.layout.oned_cell_item, allGeneration.getAllCells(), allGeneration.getSize())
        _binding = OnedCellgridFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cellGrid.numColumns = allGeneration.getSize()
        binding.cellGrid.adapter = adapter

    }
}