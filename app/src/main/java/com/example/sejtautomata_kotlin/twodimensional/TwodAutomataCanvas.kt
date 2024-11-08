package com.example.sejtautomata_kotlin.twodimensional

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import kotlin.math.max
import kotlin.math.min

class TwodAutomataCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs){
    private var allGeneration: CellularAutomata? = null

    var defaultCellSize = 20
    var currentCellSize = defaultCellSize

    private var startX = 0
    private var startY = 0
    private var deltaX = 0
    private var deltaY = 0

    private var editingX = 0
    private var editingY = 0

    private val paint = Paint()

    var isEditing = false

    private val scaleDetector: ScaleGestureDetector =
        ScaleGestureDetector(context, object: ScaleGestureDetector.SimpleOnScaleGestureListener(){
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                detector.let {
                    currentCellSize = max(10, min((defaultCellSize.toFloat() * it.scaleFactor).toInt(), 70))
                    invalidate()
                }
                return super.onScale(detector)
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) {
                super.onScaleEnd(detector)

                defaultCellSize = currentCellSize
            }
        })

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.reset()
        allGeneration?.getLastGeneration()?.let{
            for(y in 0..<it.getRowCount()){
                for(x in 0..<it.getColCount()){
                    paint.color = if(it.getCell(x, y).isActive) Color.BLACK else Color.WHITE
                    canvas.drawRect(
                        (x * currentCellSize.toFloat()) + deltaX,
                        (y * currentCellSize.toFloat()) + deltaY,
                        (x + 1) * currentCellSize.toFloat() + deltaX,
                        (y + 1) * currentCellSize.toFloat() + deltaY,
                        paint)
                }
            }
        }
        if(isEditing){
            paint.color = Color.BLUE
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 5f
            canvas.drawRect(
                (editingX * currentCellSize.toFloat()) + deltaX,
                (editingY * currentCellSize.toFloat()) + deltaY,
                (editingX + 1) * currentCellSize.toFloat() + deltaX,
                (editingY + 1) * currentCellSize.toFloat() + deltaY,
            paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                startX = event.x.toInt() - deltaX
                startY = event.y.toInt() - deltaY
            }
            MotionEvent.ACTION_MOVE -> {
                deltaX = event.x.toInt() - startX
                deltaY = event.y.toInt() - startY
            }
        }
        invalidate()
        return true
    }

    fun getAllGeneration(): CellularAutomata?{
        if(this.allGeneration == null){
            return null
        }
        return this.allGeneration!!
    }

    fun setGeneration(allGeneration: CellularAutomata){
        this.allGeneration = allGeneration
        invalidate()
    }

    fun generateNextGeneration(){
        this.allGeneration?.generateNextGeneration()
        invalidate()
    }

    fun toggleIsEditing(){
        this.isEditing = !this.isEditing
        invalidate()
    }

    fun moveCursorLeft(){
        if(!this.isEditing)
            return
        this.editingX = max(0, this.editingX - 1)
        invalidate()
    }
    fun moveCursorRight(){
        if(!this.isEditing)
            return
        this.editingX = min(this.allGeneration!!.getRows(), this.editingX + 1)
        invalidate()
    }
    fun moveCursorUp(){
        if(!this.isEditing)
            return
        this.editingY = max(0, this.editingY - 1)
        invalidate()
    }
    fun moveCursorDown(){
        if(!this.isEditing)
            return
        this.editingY = min(this.allGeneration!!.getCols(), this.editingY + 1)
        invalidate()
    }

    fun toggleCursorCell(){
        if(!this.isEditing)
            return
        this.allGeneration!!.toggleCell(editingX, editingY)
        invalidate()
    }
}