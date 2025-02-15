package com.example.sejtautomata_kotlin.onedimensional

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.example.sejtautomata_kotlin.databinding.ModifyAutomatonDialogFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ModifyAutomatonDialogFragment: DialogFragment() {
    private var _binding: ModifyAutomatonDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private var dialogView: View? = null

    private var parentFragmentCanvas: CellGridFragmentCanvas? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply{
            dialogView = onCreateView(layoutInflater, null, savedInstanceState)

            dialogView?.let{
                onViewCreated(it, savedInstanceState)
            }
            setView(dialogView)
        }.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModifyAutomatonDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var newSize = -1
        var newRule = -1

        parentFragmentCanvas = (parentFragment as CellGridFragmentCanvas)

        binding.cellSize.doOnTextChanged { text, _, _, _ ->
            newSize = try {
                text.toString().toInt()
            }catch(e: Exception){
                -1
            }
        }
        binding.ruleset.doOnTextChanged { text, _, _, _ ->
            newRule = try {
                text.toString().toInt()
            }catch(e: Exception){
                -1
            }
        }

        binding.setButton.setOnClickListener {
            newSize = if(newSize == -1){
                parentFragmentCanvas!!.binding.automata.cellularAutomata!!.getSize()
            }else{
                newSize
            }

            newRule = if(newRule == -1){
                parentFragmentCanvas!!.binding.automata.cellularAutomata!!.getRuleSet()
            }else{
                newRule
            }

            parentFragmentCanvas!!.binding.automata.cellularAutomata = CellularAutomata(newSize, newRule).apply {
                addEmptyGeneration()
            }
            parentFragmentCanvas!!.rowSize = newSize
            parentFragmentCanvas!!.ruleSet = newRule
            parentFragmentCanvas!!.binding.automata.invalidate()
            parentFragmentCanvas!!.binding.toolbar.title = "Rule: ${newRule}; Size: ${newSize}"
            Log.i("asd", "size: ${newSize}; rule: ${newRule}")
            (dialog as AlertDialog).dismiss()
        }

        binding.cancelButton.setOnClickListener {
            (dialog as AlertDialog).dismiss()
        }
    }
}