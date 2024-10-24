package com.example.sejtautomata_kotlin.onedimensional

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.sejtautomata_kotlin.R

class CellGridAdapter(
    context: Context,
    resourceId: Int,
    items: ArrayList<Cell>
): ArrayAdapter<Cell>(context, resourceId, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView: View? = convertView
        if(listItemView == null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.oned_cell_item, parent, false)
        }
        val cell: Cell? = getItem(position)
        Log.i("asd", cell!!.isActive.toString())
        val cellLayout: LinearLayout = listItemView!!.findViewById(R.id.cell)
        cellLayout.background = when(cell!!.isActive){
            false -> ContextCompat.getDrawable(context, R.drawable.cell_item_off)
            true -> ContextCompat.getDrawable(context, R.drawable.cell_item_on)
        }
        return listItemView!!
    }
}