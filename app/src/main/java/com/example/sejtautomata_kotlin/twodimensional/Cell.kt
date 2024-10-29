package com.example.sejtautomata_kotlin.twodimensional

data class Cell(
    var isActive: Boolean = false
){
    fun toggle(){
        this.isActive = !this.isActive
    }
}
