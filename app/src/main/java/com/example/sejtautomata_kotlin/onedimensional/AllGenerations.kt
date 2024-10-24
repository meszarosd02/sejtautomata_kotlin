package com.example.sejtautomata_kotlin.onedimensional

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

    fun addEmptyGeneration(count: Int = 1){
        this.generations.addAll(Collections.nCopies(count, Generation(this.size, this.ruleSet)))
    }

    fun getSize(): Int{
        return this.size
    }

    fun getRuleSet(): Int{
        return this.ruleSet
    }
}