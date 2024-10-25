package com.example.sejtautomata_kotlin.onedimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sejtautomata_kotlin.databinding.OnedCellgridFragmentRecyclerviewBinding

class CellgridFragmentRecyclerView: Fragment() {
    private var _binding: OnedCellgridFragmentRecyclerviewBinding? = null
    private val binding get() = _binding!!

    private var allGeneration = AllGenerations(50, 110)

    private var adapter: CellGridAdapterRecyclerView? = null

    private val cellList: ArrayList<Cell> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGeneration.addEmptyGeneration()
        _binding = OnedCellgridFragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cellList.addAll(allGeneration.getAllCells())

        adapter = CellGridAdapterRecyclerView(allGeneration.getSize(), cellList)
        binding.cellGridRv.adapter = adapter
        binding.cellGridRv.layoutManager = GridLayoutManager(requireContext(), allGeneration.getSize())

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
                allGeneration.generateNextGeneration()
            }
        }
        updateCellList()
    }

    private fun updateCellList(){
        adapter!!.updateItems(allGeneration.getAllCells())
    }
}