package com.example.sejtautomata_kotlin.onedimensional

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.icu.number.Scale
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet.Motion

class AutomataCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): View(context, attrs) {
    var defaultCellSize = 20
    var currentCellSize = defaultCellSize

    private var startX = 0
    private var startY = 0
    private var deltaX = 0
    private var deltaY = 0

    private val paint = Paint()

    var allGenerations: AllGenerations? = null

    private val scaleDetector: ScaleGestureDetector =
        ScaleGestureDetector(context, object: ScaleGestureDetector.SimpleOnScaleGestureListener(){
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                detector.let {

                    currentCellSize = (defaultCellSize.toFloat() * it.scaleFactor).toInt()
                    invalidate()
                    Log.i("asd", it.scaleFactor.toString())
                }
                return super.onScale(detector)
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) {
                super.onScaleEnd(detector)

                defaultCellSize = currentCellSize
            }
        })

    override fun onDraw(canvas: Canvas) {
        parent.requestDisallowInterceptTouchEvent(true)
        super.onDraw(canvas)
        allGenerations?.let {
            for (y in 0..<it.getGenerations().size) {
                for (x in 0..<it.getGenerations()[y].getCells().size) {
                    paint.color =
                        if (it.getGenerations()[y][x].isActive) Color.BLACK else Color.WHITE
                    canvas.drawRect(
                        (x * currentCellSize.toFloat()) + (deltaX),
                        (y * currentCellSize.toFloat()) + (deltaY),
                        (x + 1) * currentCellSize.toFloat() + deltaX,
                        (y + 1) * currentCellSize.toFloat() + deltaY,
                        paint
                    )
                }
            }
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
}