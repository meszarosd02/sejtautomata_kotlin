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

    var rowSize = 20
    var ruleSet = 30

    private val cellularAutomata = CellularAutomata(rowSize, ruleSet)

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

        binding.automata.cellularAutomata = cellularAutomata
        binding.automata.cellularAutomata?.apply {
            addEmptyGeneration()
            refreshCanvas()
            binding.toolbar.title = "Rule: ${this.getRuleSet()}; Size: ${this.getSize()}"
        }

        binding.canvas.let{
            it.setOnCellsChanged {
                binding.automata.apply{
                    cellularAutomata = CellularAutomata(rowSize, ruleSet).apply{
                        addGeneration(Generation(rowSize, ruleSet, it.getCells()))
                    }
                    invalidate()
                }
            }
        }

        binding.canvas.rowSize = cellularAutomata.getSize()
        binding.canvas.regenCells()

        binding.addGenerationButton.setOnClickListener {
            generateGenerations()
        }

        binding.modify.setOnClickListener {
            ModifyAutomatonDialogFragment().show(childFragmentManager, "ModifyAutomatonDialogFragment")
        }

        binding.setGenZero.setOnClickListener {
            SetGenZeroDialogFragment().show(childFragmentManager, "SetGenZeroDialogFragment")
        }
        binding.longLeft.setOnClickListener {
            binding.canvas.moveCursorIndex(-5)

        }

        binding.left.setOnClickListener {
            binding.canvas.moveCursorIndex(-1)

        }

        binding.right.setOnClickListener {
            binding.canvas.moveCursorIndex(1)
        }

        binding.longRight.setOnClickListener {
            binding.canvas.moveCursorIndex(5)
        }

        binding.toggle.setOnClickListener {
            binding.canvas.toggleCell()
        }
    }

    fun newRandomGenerationZero() {
        binding.automata.cellularAutomata = CellularAutomata(rowSize, ruleSet).apply{
            randomGenerationZero()
            binding.canvas.setCells(this.getAllCells())
        }
        refreshCanvas()
    }

    fun newLastCellGenerationZero() {
        binding.automata.cellularAutomata = CellularAutomata(rowSize, ruleSet).apply{
            lastCellGenerationZero()
            binding.canvas.setCells(this.getAllCells())
        }
        refreshCanvas()
    }

    fun newNthCellGenerationZero(n: Int){
        binding.automata.cellularAutomata = CellularAutomata(rowSize, ruleSet).apply{
            nthCellGenerationZero(n)
            binding.canvas.setCells(this.getAllCells())
        }
        refreshCanvas()
    }
    fun newCenterCellGenerationZero(){
        binding.automata.cellularAutomata = CellularAutomata(rowSize, ruleSet).apply{
            centerCellGenerationZero()
            binding.canvas.setCells(this.getAllCells())
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
                binding.automata.cellularAutomata?.generateNextGeneration()
            }
        }
        refreshCanvas()

    }

    private fun refreshCanvas(){
        binding.automata.requestLayout()
        binding.automata.invalidate()
    }
}