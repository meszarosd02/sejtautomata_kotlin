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
import com.example.sejtautomata_kotlin.databinding.SetGenZeroDialogFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SetGenZeroDialogFragment: DialogFragment() {
    private var _binding: SetGenZeroDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private var dialogView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SetGenZeroDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply{
            dialogView = onCreateView(layoutInflater, null, savedInstanceState)

            dialogView?.let{
                onViewCreated(it, savedInstanceState)
            }
            setView(dialogView)
        }.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentFragmentCanvas = (parentFragment as CellGridFragmentCanvas)

        var n: Int = 0
        binding.nthCell.doOnTextChanged { text, _, _, _ ->
            n = try {
                text.toString().toInt()
            }catch(e: Exception){
                1
            }
        }

        binding.randomButton.setOnClickListener {
            parentFragmentCanvas.newRandomGenerationZero()
            (dialog as AlertDialog).dismiss()
        }

        binding.lastCellButton.setOnClickListener {
            parentFragmentCanvas.newLastCellGenerationZero()
            (dialog as AlertDialog).dismiss()
        }
        binding.nthButton.setOnClickListener {
            parentFragmentCanvas.newNthCellGenerationZero(n)
            Log.i("asd", n.toString())
            (dialog as AlertDialog).dismiss()
        }
    }
}