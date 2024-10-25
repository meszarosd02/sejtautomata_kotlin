package com.example.sejtautomata_kotlin.onedimensional

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class AutomataCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): View(context, attrs) {
    val cellSize = 20
    private val paint = Paint()

    var allGenerations: AllGenerations? = null

    override fun onDraw(canvas: Canvas) {
        Log.i("asd", "onDraw called")
        parent.requestDisallowInterceptTouchEvent(true)
        super.onDraw(canvas)
        allGenerations?.let {
            for (y in 0..<it.getGenerations().size) {
                for (x in 0..<it.getGenerations()[y].getCells().size) {
                    paint.color =
                        if (it.getGenerations()[y][x].isActive) Color.BLACK else Color.WHITE
                    canvas.drawRect(
                        x * cellSize.toFloat(),
                        y * cellSize.toFloat(),
                        (x + 1) * cellSize.toFloat(),
                        (y + 1) * cellSize.toFloat(),
                        paint
                    )
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return false
    }
}