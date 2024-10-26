package com.example.sejtautomata_kotlin.onedimensional

import java.util.Collections
import kotlin.random.Random

class Generation {
    private var size: Int
    private var cells: ArrayList<Cell> = ArrayList()
    private var ruleSet: Int

    constructor(size: Int, ruleSet: Int){
        this.size = size
        this.ruleSet = ruleSet
        for(i in 0..<this.size){
            this.cells.add(Cell(false))
        }
    }

    constructor(size: Int, ruleSet: Int, cells: ArrayList<Cell>){
        this.size = size
        this.ruleSet = ruleSet
        this.cells = cells
    }

    fun getSize(): Int{
        return this.size
    }

    fun getRuleSet(): Int{
        return this.ruleSet
    }

    fun getCells(): ArrayList<Cell>{
        return this.cells
    }

    operator fun get(item: Int): Cell{
        return this.cells[item]
    }

    override fun toString(): String {
        var result = ""
        for(cell in this.cells){
            result += if(cell.isActive) "1" else "0"
        }
        return result
    }
}