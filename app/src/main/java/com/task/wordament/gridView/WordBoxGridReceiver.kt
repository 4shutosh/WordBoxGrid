package com.task.wordament.gridView

import com.task.wordament.wordBox.WordBoxButtonState

interface WordBoxGridReceiver {

    fun setSelected(
        index: Int,
        state: WordBoxButtonState
    )

    fun actionRelease(list: ArrayList<Int>)

}