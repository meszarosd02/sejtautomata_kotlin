package com.example.sejtautomata_kotlin.onedimensional

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sejtautomata_kotlin.R
import com.example.sejtautomata_kotlin.databinding.OnedCellItemBinding

class CellGridAdapterRecyclerView(private val generationSize: Int, private var cells: ArrayList<Cell>): RecyclerView.Adapter<CellGridAdapterRecyclerView.ViewHolder>() {
    class ViewHolder(private var binding: OnedCellItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(c: Cell){
            binding.cell.background = when(c.isActive){
                true -> ContextCompat.getDrawable(binding.cell.context, R.drawable.cell_item_on)
                false -> ContextCompat.getDrawable(binding.cell.context, R.drawable.cell_item_off)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OnedCellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cells.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = cells[position]
        val displayMetrics = holder.itemView.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val itemWidth = screenWidth / generationSize
        holder.itemView.layoutParams = AbsListView.LayoutParams(itemWidth, itemWidth)
        holder.bind(current)
    }

    fun updateItems(newCells: ArrayList<Cell>){
        val diffCallback = object: DiffUtil.Callback(){
            override fun getOldListSize() = cells.size

            override fun getNewListSize() = newCells.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return cells[oldItemPosition] == newCells[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return cells[oldItemPosition].isActive == newCells[newItemPosition].isActive
            }

        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        cells = newCells
        diffResult.dispatchUpdatesTo(this)
    }
}