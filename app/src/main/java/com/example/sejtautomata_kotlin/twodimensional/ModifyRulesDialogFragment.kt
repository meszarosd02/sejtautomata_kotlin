package com.example.sejtautomata_kotlin.twodimensional

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sejtautomata_kotlin.databinding.ModifyAutomatonDialogFragmentBinding
import com.example.sejtautomata_kotlin.databinding.ModifyRulesDialogFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ModifyRulesDialogFragment: DialogFragment() {
    private var _binding: ModifyRulesDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private var dialogView: View? = null

    private var adapter: RulesAdapter? = null
    private val rules: ArrayList<Rule> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rules.add(Rule(Rule.COMPARISON.EQUAL, 2, Rule.STARTSTATE.ANY, true))
        _binding = ModifyRulesDialogFragmentBinding.inflate(inflater, container, false)
        adapter = RulesAdapter(rules)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply{
            dialogView = onCreateView(layoutInflater, null, savedInstanceState)
            dialogView?.let {
                onViewCreated(it, savedInstanceState)
            }
            setView(dialogView)
        }.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}