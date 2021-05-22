package com.task.wordament.gridView

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WordBoxGridDecoration(
    private val margin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = margin
        outRect.bottom = margin
        outRect.top = margin
        outRect.left = margin
    }
}