package com.task.wordament.gridView

import androidx.annotation.CheckResult

interface WordBoxGridReceiver {

    fun setSelected(
        index: Int,
        selected: Boolean
    )

    fun actionRelease(list: ArrayList<Int>)

    @CheckResult
    fun isSelected(index: Int): Boolean

}