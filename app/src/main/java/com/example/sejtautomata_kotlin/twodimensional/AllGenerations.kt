package com.example.sejtautomata_kotlin.twodimensional

import android.util.Log

class AllGenerations {
    private val generations: ArrayList<Generation> = ArrayList()
    private var rows: Int = 0
    private var cols: Int = 0
    private val rules: ArrayList<Rule> = ArrayList()

    constructor(rows: Int, cols: Int){
        this.rows = rows
        this.cols = cols
    }

    fun addGeneration(generation: Generation){
        this.generations.add(generation)
    }

    fun getLastGeneration(): Generation {
        return generations.last()
    }

    private fun parseStartState(cell: Cell, rule: Rule): Boolean{
        return when(rule.start){
            Rule.STARTSTATE.ANY -> {return true}
            Rule.STARTSTATE.ACTIVE -> {return cell.isActive}
            Rule.STARTSTATE.INACTIVE -> {return !cell.isActive}
        }
    }

    fun generateNextGeneration(){
        val newGeneration = Generation(this.rows, this.cols)
        val lastGeneration = getLastGeneration()
        for(x in 0..<lastGeneration.getRowCount()){
            for(y in 0..<lastGeneration.getColCount()){
                Log.i("cell", "x: ${x}; y: $y; n: ${lastGeneration.getCellNeighbors(x, y)}")
                rules@ for(rule in this.rules){
                    when(rule.comp){
                        Rule.COMPARISON.LESS -> {
                            if(lastGeneration.getCellNeighbors(x, y) < rule.num &&
                                parseStartState(lastGeneration.getCell(x, y), rule)) {
                                newGeneration[x][y].isActive = rule.result
                                break@rules
                            }
                            else
                                newGeneration[x][y].isActive = lastGeneration.getCell(x, y).isActive
                        }
                        Rule.COMPARISON.LESS_EQUAL -> {
                            if(lastGeneration.getCellNeighbors(x, y) <= rule.num &&
                                parseStartState(lastGeneration.getCell(x, y), rule)) {
                                newGeneration[x][y].isActive = rule.result
                                break@rules
                            }
                            else
                                newGeneration[x][y].isActive = lastGeneration.getCell(x, y).isActive
                        }
                        Rule.COMPARISON.EQUAL -> {
                            if(lastGeneration.getCellNeighbors(x, y) == rule.num &&
                                parseStartState(lastGeneration.getCell(x, y), rule)){
                                newGeneration[x][y].isActive = rule.result
                                break@rules
                            }
                            else
                                newGeneration[x][y].isActive = lastGeneration.getCell(x, y).isActive
                        }
                        Rule.COMPARISON.GREATER_EQUAL -> {
                            if(lastGeneration.getCellNeighbors(x, y) >= rule.num &&
                                parseStartState(lastGeneration.getCell(x, y), rule)){
                                newGeneration[x][y].isActive = rule.result
                                break@rules
                            }
                            else
                                newGeneration[x][y].isActive = lastGeneration.getCell(x, y).isActive
                        }
                        Rule.COMPARISON.GREATER -> {
                            if(lastGeneration.getCellNeighbors(x, y) > rule.num &&
                                parseStartState(lastGeneration.getCell(x, y), rule)){
                                newGeneration[x][y].isActive = rule.result
                                break@rules
                            }
                            else
                                newGeneration[x][y].isActive = lastGeneration.getCell(x, y).isActive
                        }
                    }
                }
            }
        }
        this.generations.add(newGeneration)
    }

    fun addRule(rule: Rule) {
        this.rules.add(rule)
    }
}