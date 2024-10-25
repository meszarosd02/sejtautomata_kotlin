package com.example.sejtautomata_kotlin.onedimensional

import kotlin.random.Random

class Generation {
    private var size: Int
    private var cells: ArrayList<Cell> = ArrayList()
    private var ruleSet: Int
    private var previousGeneration: Generation? = null

    constructor(size: Int, ruleSet: Int){
        this.size = size
        this.ruleSet = ruleSet
        for(i in 0..<size){
            this.cells.add(Cell(when(i % 2){
                1 -> true
                else -> false
            }))
        }
    }

    constructor(size: Int, ruleSet: Int, cells: ArrayList<Cell>){
        this.size = size
        this.ruleSet = ruleSet
        this.cells = cells
    }

    constructor(previousGeneration: Generation){
        this.size = previousGeneration.getSize()
        this.ruleSet = previousGeneration.getRuleSet()
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
        return cells[item]
    }
}