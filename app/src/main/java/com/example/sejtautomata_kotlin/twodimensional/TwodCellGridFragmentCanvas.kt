package com.example.sejtautomata_kotlin.twodimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sejtautomata_kotlin.databinding.TwodCellgridFragmentCanvasBinding
import com.example.sejtautomata_kotlin.onedimensional.ModifyAutomatonDialogFragment

class TwodCellGridFragmentCanvas: Fragment() {
    private var _binding: TwodCellgridFragmentCanvasBinding? = null
    private val binding get() = _binding!!

    private var rows = 20
    private var cols = 20

    var allGenerations: CellularAutomata = CellularAutomata(rows, cols)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TwodCellgridFragmentCanvasBinding.inflate(inflater, container, false)
        /*allGenerations.addRule(Rule(Rule.COMPARISON.LESS, 2, Rule.STARTSTATE.ACTIVE, false))
        allGenerations.addRule(Rule(Rule.COMPARISON.EQUAL, 2, Rule.STARTSTATE.ACTIVE, true))
        allGenerations.addRule(Rule(Rule.COMPARISON.EQUAL, 3, Rule.STARTSTATE.ANY, true))
        allGenerations.addRule(Rule(Rule.COMPARISON.GREATER, 3, Rule.STARTSTATE.ACTIVE, false))*/
        allGenerations.addGeneration(Generation(rows, cols))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.canvas.setGeneration(allGenerations)

        binding.rules.setOnClickListener {
            RulesBottomSheetDialog().show(childFragmentManager, "RulesBottomSheetDialog")
        }

        binding.nextGen.setOnClickListener {
            binding.canvas.generateNextGeneration()
        }

        binding.toggleModify.setOnClickListener {
            binding.canvas.toggleIsEditing()
            binding.navigation.visibility = when(binding.canvas.isEditing){
                true -> View.VISIBLE
                false -> View.GONE
            }
        }

        binding.resetBoard.setOnClickListener {
            binding.canvas.setGeneration(CellularAutomata(rows, cols))
        }

        binding.left.setOnClickListener {
            binding.canvas.moveCursorLeft()
        }

        binding.right.setOnClickListener {
            binding.canvas.moveCursorRight()
        }

        binding.up.setOnClickListener {
            binding.canvas.moveCursorUp()
        }

        binding.down.setOnClickListener {
            binding.canvas.moveCursorDown()
        }

        binding.toggle.setOnClickListener {
            binding.canvas.toggleCursorCell()
        }
    }
}