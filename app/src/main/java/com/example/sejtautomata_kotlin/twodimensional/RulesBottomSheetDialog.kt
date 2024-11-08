package com.example.sejtautomata_kotlin.twodimensional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sejtautomata_kotlin.databinding.ModifyRulesDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RulesBottomSheetDialog: BottomSheetDialogFragment() {
    private var _binding: ModifyRulesDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: RulesAdapter? = null

    private var rules: ArrayList<Rule> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModifyRulesDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rules = (parentFragment as TwodCellGridFragmentCanvas).allGenerations.rules
        adapter = RulesAdapter(rules)

        binding.rulesRv.adapter = adapter
        binding.rulesRv.layoutManager = LinearLayoutManager(requireContext())
    }
}