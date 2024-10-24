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

    private var generationZero = Generation(20)

    private var adapter: CellGridAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = CellGridAdapter(requireContext(), R.layout.oned_cell_item, generationZero.getCells())
        _binding = OnedCellgridFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cellGrid.numColumns = generationZero.getSize()
        binding.cellGrid.adapter = adapter

    }
}