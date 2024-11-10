package com.example.sejtautomata_kotlin

import com.example.sejtautomata_kotlin.twodimensional.Generation
import org.junit.Test
import org.junit.Assert.*

class GenerationUnitTest {
    @Test
    fun newNeighborTest(){
        val gen: Generation = Generation(10, 10)
        gen[3][1].isActive = true
        gen[3][2].isActive = true
        gen[3][3].isActive = true

        assertEquals(3, gen.getCellNeighbors2(2, 2))
        assertEquals(2, gen.getCellNeighbors2(2, 1))
        assertEquals(2, gen.getCellNeighbors2(2, 3))
        assertEquals(1, gen.getCellNeighbors2(2, 4))
        assertEquals(1, gen.getCellNeighbors2(2, 0))
        assertEquals(0, gen.getCellNeighbors2(6, 6))
    }
}