package com.example.sejtautomata_kotlin.onedimensional

import android.util.Log
import java.util.Collections

class AllGenerations {
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

    fun generateNextGeneration(){
        val newGeneration = Generation(this.size, this.ruleSet)
        val ruleSetBinary = ruleSetIntToBinary()
        for(i in 0..<this.size){
            val left = getLastGeneration()[(i+this.size-1)%this.size].isActive
            val center = getLastGeneration()[(i+this.size)%this.size].isActive
            val right = getLastGeneration()[(i+this.size+1)%this.size].isActive

            if(left && center && right){
                if(ruleSetBinary[0].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
            if(left && center && !right){
                if(ruleSetBinary[1].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
            if(left && !center && right){
                if(ruleSetBinary[2].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
            if(left && !center && !right){
                if(ruleSetBinary[3].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
            if(!left && center && right){
                if(ruleSetBinary[4].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
            if(!left && center && !right){
                if(ruleSetBinary[5].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
            if(!left && !center && right){
                if(ruleSetBinary[6].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
            if(!left && !center && !right){
                if(ruleSetBinary[7].toString() == "1"){
                    newGeneration[i].isActive = true
                }
            }
        }
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
}