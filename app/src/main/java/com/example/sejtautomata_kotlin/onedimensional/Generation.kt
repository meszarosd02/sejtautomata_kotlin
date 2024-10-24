package com.example.sejtautomata_kotlin.onedimensional

class Generation {
    private var size: Int
    private var cells: ArrayList<Cell> = ArrayList()


    constructor(size: Int){
        this.size = size
        for(i in 0..<size){
            this.cells.add(Cell())
        }
    }

    fun getSize(): Int{
        return this.size
    }

    fun getCells(): ArrayList<Cell>{
        return this.cells
    }
}