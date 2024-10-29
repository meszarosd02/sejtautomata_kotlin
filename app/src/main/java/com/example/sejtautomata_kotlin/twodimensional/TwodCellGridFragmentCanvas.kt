package com.example.sejtautomata_kotlin.twodimensional

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sejtautomata_kotlin.databinding.TwodCellgridFragmentCanvasBinding

class TwodCellGridFragmentCanvas: Fragment() {
    private var _binding: TwodCellgridFragmentCanvasBinding? = null
    private val binding get() = _binding!!

    private var rows = 20
    private var cols = 20

    private var generation = Generation(rows, cols)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TwodCellgridFragmentCanvasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.canvas.generation = generation
        binding.canvas.invalidate()

        Log.i("asd", if(binding.canvas.generation!!.getCell(0, 0).isActive) "1" else "0")
    }
}