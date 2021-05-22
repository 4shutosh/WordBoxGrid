package com.task.wordament.gridView

import android.graphics.Rect
import android.view.MotionEvent
import android.view.MotionEvent.*
import androidx.recyclerview.widget.RecyclerView
import com.task.wordament.gridView.WordBoxGridReceiver

class WordBoxGridTouchListener(
    private val receiver: WordBoxGridReceiver
) : RecyclerView.OnItemTouchListener {

    private var lastDragged = -1

    private lateinit var recyclerViewBoundRectangle: Rect

    private var selectedInOrder = arrayListOf<Int>()

    override fun onInterceptTouchEvent(view: RecyclerView, event: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(view: RecyclerView, event: MotionEvent) {
        when (event.action) {
            ACTION_MOVE -> {
                if (!recyclerViewBoundRectangle.contains(event.x.toInt(), event.y.toInt())) {
                    // finger moved out of view
                    return
                }

                val currentPosition = view.getItemPosition(event)
                if (currentPosition != RecyclerView.NO_POSITION) {
                    if (lastDragged == currentPosition) return

                    if (selectedInOrder.isNotEmpty()) {
                        if (selectedInOrder.contains(currentPosition) && selectedInOrder.lastIndex != currentPosition) {
                            val flag =
                                (selectedInOrder[selectedInOrder.lastIndex - 1] == currentPosition)
                            if (flag) {
                                receiver.setSelected(lastDragged, !receiver.isSelected(lastDragged))
                                selectedInOrder.remove(lastDragged)
                                lastDragged = currentPosition
                                return
                            } else {
                                return
                            }
                        }
                    }
                    // active part
                    lastDragged = currentPosition
                    selectedInOrder.add(lastDragged)
//                    receiver.setSelected(lastDragged, !receiver.isSelected(lastDragged))
                    receiver.setSelected(lastDragged, true)
                    return
                }
            }
            ACTION_DOWN -> {
                recyclerViewBoundRectangle = Rect(view.left, view.top, view.right, view.bottom)
            }
            ACTION_UP -> {
                // deselect all
                lastDragged = -1
                receiver.actionRelease(selectedInOrder)
                selectedInOrder.clear()
                return
            }
        }
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}

internal fun RecyclerView.getItemPosition(e: MotionEvent): Int {
    val v = findChildViewUnder(e.x, e.y) ?: return RecyclerView.NO_POSITION
    return getChildAdapterPosition(v)
}
