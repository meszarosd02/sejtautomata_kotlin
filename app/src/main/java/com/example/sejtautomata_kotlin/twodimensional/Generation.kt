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
    }

    fun getCellNeighbors(x: Int, y: Int): Int{
        var sum = 0
        for(i in x-1..x+1){
            for(j in y-1..y+1){
                if(!(i == x && j == y)){
                    sum += if(cells[(i + cols) % cols][(j + rows) % rows].isActive) 1 else 0
                }
            }
        }
        return sum
    }

    fun getCellNeighbors2(x: Int, y: Int): Int{
        val list =  (x-1..x+1).flatMap { i ->
            (y-1..y+1).map{ j ->
                if (!(i == x && j == y)) this[x][y] else Cell(false)
            }
        }
        return list.count { cell ->
            cell.isActive
        }
    }

    fun getCellRow(pos: Int): ArrayList<Cell>{
        return cells[pos]
    }

    fun getCell(x: Int, y: Int): Cell{
        return this.cells[x][y]
    }

    fun setCell(x: Int, y: Int, cell: Cell){
        this.cells[x][y] = cell
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