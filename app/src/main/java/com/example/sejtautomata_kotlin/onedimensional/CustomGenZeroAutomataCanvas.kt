package com.example.sejtautomata_kotlin.onedimensional

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.max
import kotlin.math.min

class CustomGenZeroAutomataCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): View(context, attrs) {
    private val cellWindow: Int = 10
    var rowSize = 0
    private val cells: ArrayList<Cell> = ArrayList<Cell>().apply{
        for(i in 0..<rowSize){
            add(Cell(false))
        }
    }

    private var selectedCellIndex: Int = 0
    private var cameraStart: Int = 0
    private var cursorIndex: Int = 0

    private var cellSize: Int = 0

    private val paint = Paint()

    private var onCellsChanged = {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.reset()
        cellSize = min(width / cellWindow, 50)
        paint.textSize = cellSize.toFloat() / 2
        for(i in 0..<cellWindow){
            //Indices
            paint.color = Color.BLACK
            canvas.drawText("${i+cameraStart}",
                i * cellSize.toFloat() + (cellSize / 2),
                (cellSize / 2f),
                paint)
            paint.color = if(cells[i+cameraStart].isActive) Color.BLACK else Color.WHITE
            paint.style = Paint.Style.FILL
            //Cells
            canvas.drawRect(
                i * cellSize.toFloat(),
                cellSize.toFloat(),
                i * cellSize.toFloat() + cellSize,
                cellSize * 2f,
                paint)
        }
        //Cursor
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        selectedCellIndex.let{
            canvas.drawRect(
                (cursorIndex) * cellSize.toFloat(),
                cellSize.toFloat(),
                (cursorIndex) * cellSize.toFloat() + cellSize,
                cellSize * 2f,
                paint
            )
        }

    }

    fun regenCells(){
        this.cells.apply{
            clear()
            for(i in 0..<rowSize){
                add(Cell(false))
            }
        }
    }

    fun moveCursorIndex(position: Int){
        val centerInsideWindow = cellWindow / 2
        if(selectedCellIndex + position >= rowSize){
            selectedCellIndex = rowSize - 1
            cursorIndex = cellWindow - 1
            invalidate()
            return
        }
        if(selectedCellIndex + position < 0){
            selectedCellIndex = 0
            cursorIndex = 0
            invalidate()
            return
        }
        selectedCellIndex += position
        val isStart = cameraStart == 0 && selectedCellIndex <= centerInsideWindow
        val isEnd = (cameraStart + cellWindow) == rowSize
        if(isStart){
            cursorIndex = min(centerInsideWindow, selectedCellIndex)
        }
        if(isEnd){
            cursorIndex = max(centerInsideWindow, selectedCellIndex - cameraStart)
            cameraStart = min(selectedCellIndex - centerInsideWindow, rowSize - cellWindow)
        }
        if((!isStart && !isEnd) || selectedCellIndex == centerInsideWindow) {
            cameraStart = min(max(selectedCellIndex - centerInsideWindow, 0), rowSize - cellWindow)
            cursorIndex = min(centerInsideWindow, selectedCellIndex)
        }
        invalidate()
    }

    fun moveCursorIndex1(position: Int){
        val centerInsideWindow = cellWindow / 2
        if(selectedCellIndex + position >= rowSize){
            return
        }
        selectedCellIndex += position
        val isStart = cameraStart == 0
        val isEnd = (cameraStart + cellWindow) == rowSize
        if(cameraStart + position > cameraStart + centerInsideWindow){
            cameraStart += (position - centerInsideWindow)
            cursorIndex = selectedCellIndex - cameraStart
            invalidate()
            return
        }
        if(isStart || isEnd){
            if(isStart){
                if(selectedCellIndex > cameraStart + centerInsideWindow + 1){
                    cameraStart += position
                }else if(selectedCellIndex > cameraStart + centerInsideWindow){
                    cameraStart += (selectedCellIndex - centerInsideWindow)
                }
            }else if(isEnd){
                if(selectedCellIndex + position < cameraStart + centerInsideWindow - 1){
                    cameraStart += position
                }
            }
        }else{
            cameraStart += max(position, cameraStart + position - rowSize - 1)
        }
        cursorIndex = selectedCellIndex - cameraStart
        invalidate()
    }

    fun setCells(cells: ArrayList<Cell>){
        this.cells.clear()
        this.cells.addAll(cells)
        invalidate()
    }

    fun addToCells(cells: ArrayList<Cell>){
        this.cells.addAll(cells)
        invalidate()
    }

    fun toggleCell(){
        this.cells[selectedCellIndex].toggle()
        onCellsChanged()
        invalidate()
    }

    fun getCells(): ArrayList<Cell>{
        return this.cells
    }

    fun setOnCellsChanged(function: () -> Unit){
        this.onCellsChanged = function
    }
}