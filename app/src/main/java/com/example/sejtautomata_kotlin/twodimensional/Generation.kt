package com.example.sejtautomata_kotlin.twodimensional

import com.example.sejtautomata_kotlin.onedimensional.Cell

class Generation {
    private var rows: Int = 0
    private var cols: Int = 0
    private var cells: ArrayList<ArrayList<Cell>> = ArrayList()

    constructor(rows: Int, cols: Int){
        this.rows = rows
        this.cols = cols
        for(x in 0..<cols){
            cells.add(ArrayList())
            for(y in 0..<rows){
                cells[x].add(Cell((x % 2 + y % 2) % 2 == 0))
            }
        }
    }

    fun getCellRow(pos: Int): ArrayList<Cell>{
        return cells[pos]
    }

    fun getCell(x: Int, y: Int): Cell{
        return this.cells[x][y]
    }

    fun getRowCount(): Int{
        return this.rows
    }
    fun getColCount(): Int{
        return this.cols
    }
}