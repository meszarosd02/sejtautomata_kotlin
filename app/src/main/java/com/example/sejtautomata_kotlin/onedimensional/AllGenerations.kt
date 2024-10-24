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
        for(i in 0..<this.size){
            newGeneration[i].isActive = when(getLastGeneration()[(i+this.size-1)%this.size].isActive){
                false -> false
                true -> true
            } && when(getLastGeneration()[(i+this.size)%this.size].isActive){
                false -> true
                true -> false
            } && when(getLastGeneration()[(i+this.size+1)%this.size].isActive){
                false -> true
                true -> false
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
}