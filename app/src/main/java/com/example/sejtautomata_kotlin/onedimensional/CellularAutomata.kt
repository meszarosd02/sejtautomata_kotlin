package com.example.sejtautomata_kotlin.onedimensional

import android.util.Log
import java.util.Collections
import kotlin.random.Random

class CellularAutomata {
    private var generations: ArrayList<Generation> = ArrayList()
    private var size: Int
    private var ruleSet: Int

    constructor(size: Int, ruleSet: Int){
        this.size = size
        this.ruleSet = ruleSet
    }

    fun addGeneration(generation: Generation){
        this.generations.add(generation)
    }

    fun getLastGeneration(): Generation{
        return generations.last()
    }

    fun generateNextGenerationTwo(){
        val ruleMap: Map<String, Int> = (0..7).associate { i ->
            val pattern = String.format("%3s", Integer.toBinaryString(i)).replace(" ", "0")
            val result = (this.ruleSet shr i) and 1
            pattern to result
        }
        val newGeneration = Generation(this.size, this.ruleSet)
        for(i in 0..<this.size){
            val left = if(getLastGeneration()[(i+this.size-1)%this.size].isActive) 1 else 0
            val center = if(getLastGeneration()[(i+this.size)%this.size].isActive) 1 else 0
            val right = if(getLastGeneration()[(i+this.size+1)%this.size].isActive) 1 else 0
            val pattern = "$left$center$right"
            val result = ruleMap[pattern]
            newGeneration[i].isActive = result == 1
        }
        addGeneration(newGeneration)
    }

    fun generateNextGeneration(){
        val newGeneration = Generation(this.size, this.ruleSet)
        val ruleSetBinary = ruleSetIntToBinary()
        for(i in 0..<this.size){
            val left = getLastGeneration()[(i+this.size-1)%this.size].isActive
            val center = getLastGeneration()[(i+this.size)%this.size].isActive
            val right = getLastGeneration()[(i+this.size+1)%this.size].isActive
            if(left && center && right){
                newGeneration[i].isActive = (ruleSetBinary[0].toString() != "0")
            }
            if(left && center && !right){
                newGeneration[i].isActive = (ruleSetBinary[1].toString() != "0")
            }
            if(left && !center && right){
                newGeneration[i].isActive = (ruleSetBinary[2].toString() != "0")
            }
            if(left && !center && !right){
                newGeneration[i].isActive = (ruleSetBinary[3].toString() != "0")
            }
            if(!left && center && right){
                newGeneration[i].isActive = (ruleSetBinary[4].toString() != "0")
            }
            if(!left && center && !right){
                newGeneration[i].isActive = (ruleSetBinary[5].toString() != "0")
            }
            if(!left && !center && right){
                newGeneration[i].isActive = (ruleSetBinary[6].toString() != "0")
            }
            if(!left && !center && !right){
                newGeneration[i].isActive = (ruleSetBinary[7].toString() != "0")
            }
        }
        Log.i("asd", newGeneration.toString())
        addGeneration(newGeneration)
    }

    fun addEmptyGeneration(count: Int = 1){
        this.generations.addAll(Collections.nCopies(count, Generation(this.size, this.ruleSet)))
    }

    fun getSize(): Int{
        return this.size
    }

    fun getRuleSet(): Int{
        return this.ruleSet
    }

    fun getGenerations(): ArrayList<Generation>{
        return this.generations
    }

    fun getAllCells(): ArrayList<Cell>{
        val cellList: ArrayList<Cell> = ArrayList()
        for(i in this.generations){
            cellList.addAll(i.getCells())
        }
        return cellList
    }

    private fun ruleSetIntToBinary(): String{
        var buffer = this.ruleSet
        var result = ""
        for(i in 0..7){
            result += (buffer%2).toString()
            buffer /= 2
        }
        return result.reversed()
    }

    fun randomGenerationZero() {
        this.generations.clear()
        val newGen: ArrayList<Cell> = ArrayList()
        val rand = Random(System.currentTimeMillis())
        for(i in 0..<this.size){
            newGen.add(Cell(rand.nextBoolean()))
        }
        this.generations.add(Generation(this.size, this.ruleSet, newGen))
    }

    fun lastCellGenerationZero() {
        this.generations.clear()
        val newGen: ArrayList<Cell> = ArrayList()
        for(i in 0..<this.size){
            newGen.add(Cell(i == this.size-1))
        }
        this.generations.add(Generation(this.size, this.ruleSet, newGen))
    }

    fun nthCellGenerationZero(n: Int) {
        this.generations.clear()
        val newGen: ArrayList<Cell> = ArrayList()
        for(i in 0..<this.size){
            newGen.add(Cell(i % n == 0))
        }
        this.generations.add(Generation(this.size, this.ruleSet, newGen))
    }

    fun centerCellGenerationZero(){
        this.generations.clear()
        val newGen: ArrayList<Cell> = ArrayList()
        for(i in 0..<this.size){
            newGen.add(Cell(i == this.size / 2))
        }
        this.generations.add(Generation(this.size, this.ruleSet, newGen))
    }
}