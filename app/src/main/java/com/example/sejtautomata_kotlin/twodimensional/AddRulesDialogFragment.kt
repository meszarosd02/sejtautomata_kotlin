package com.example.sejtautomata_kotlin.twodimensional

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.example.sejtautomata_kotlin.R
import com.example.sejtautomata_kotlin.databinding.AddRuleDialogFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class AddRulesDialogFragment: DialogFragment() {
    private var _binding: AddRuleDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private var dialogView: View? = null

    private var isRuleAdded: Boolean = false

    private var comparisonNumber: Int = 0
    private var selectedComparison: Rule.COMPARISON = Rule.COMPARISON.LESS
    private var selectedResult: Boolean = false
    private var selectedStartState: Rule.STARTSTATE = Rule.STARTSTATE.ANY

    private var currentRule: Rule? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddRuleDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply {
            dialogView = onCreateView(layoutInflater, null, savedInstanceState)
            dialogView?.let{
                onViewCreated(it, savedInstanceState)
            }
            setView(dialogView)
        }.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val conditionAdapter: ArrayAdapter<Rule.COMPARISON> =
            ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, Rule.COMPARISON.entries)
        val cellStateAdapter: ArrayAdapter<Rule.STARTSTATE> =
            ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, Rule.STARTSTATE.entries)
        val resultAdapter: ArrayAdapter<Boolean> =
            ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, listOf(true, false))
        binding.spinnerCondition.adapter = conditionAdapter
        binding.spinnerCellState.adapter = cellStateAdapter
        binding.spinnerResult.adapter = resultAdapter

        binding.spinnerCondition.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedComparison = Rule.COMPARISON.entries[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedComparison = Rule.COMPARISON.LESS
            }
        }

        binding.spinnerResult.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedResult = listOf(true, false)[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedResult = false
            }

        }

        binding.spinnerCellState.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedStartState = Rule.STARTSTATE.entries[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedStartState = Rule.STARTSTATE.ANY
            }

        }

        binding.inputNumber.doOnTextChanged { text, _, _, _ ->
            comparisonNumber = if(text.toString() == "") 0 else text.toString().toInt()
        }

        binding.setButton.setOnClickListener {
            isRuleAdded = true
            Log.i("rule", binding.spinnerCondition.selectedItem.toString())
            currentRule = Rule(
                selectedComparison,
                comparisonNumber,
                selectedStartState,
                selectedResult)
            (dialog as AlertDialog).dismiss()
        }
        binding.cancelButton.setOnClickListener {
            isRuleAdded = false
            (dialog as AlertDialog).dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        if(isRuleAdded){
            (parentFragment as RulesBottomSheetDialog).addToRules(currentRule!!)
            (parentFragment as RulesBottomSheetDialog).adapter!!.notifyDataSetChanged()
        }
    }
}