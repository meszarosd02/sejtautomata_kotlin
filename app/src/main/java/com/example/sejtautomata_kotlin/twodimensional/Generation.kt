package com.example.sejtautomata_kotlin.twodimensional

import android.util.Log

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
                cells[x].add(Cell(false))
            }
        }
        cells[1][1].toggle()
        cells[1][2].toggle()
        cells[1][3].toggle()
    }

    fun getCellNeighbors(x: Int, y: Int): Int{
        var sum = 0
        for(i in x-1..x+1){
            for(j in y-1..y+1){
                if(!(i == x && j == y)){
                    sum += if(cells[(i + cols) % cols][(j + rows) % rows].isActive) 1 else 0
                }
                Log.i("cica1", "x: ${(i + cols) % cols}; y: ${(j + rows) % rows} isActive: ${getCell((i + cols) % cols, (j + rows) % rows).isActive}")
            }
        }
        return sum
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

    operator fun get(item: Int): ArrayList<Cell> {
        return this.cells[item]
    }
}