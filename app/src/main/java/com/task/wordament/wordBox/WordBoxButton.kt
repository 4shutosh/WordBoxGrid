package com.task.wordament.wordBox

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.task.wordament.R
import com.task.wordament.databinding.ItemWordButtonBinding
import com.task.wordament.wordBox.WordBoxButtonState.*

class WordBoxButton @JvmOverloads constructor(
    context: Context,
    attribute: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attribute, defStyleAttr) {

    // todo make text size dynamic

    private var binding: ItemWordButtonBinding
    private var isCorrect: Boolean = false
    private var isIncorrect: Boolean = false

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ItemWordButtonBinding.inflate(inflater, this, true)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isCorrect) mergeDrawableStates(drawableState, WORD_BOX_STATUS_CORRECT)
        if (isIncorrect) mergeDrawableStates(drawableState, WORD_BOX_STATUS_INCORRECT)
        return drawableState
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    fun setData(data: WordButtonData) {
        binding.data = data
    }

    fun setState(state: WordBoxButtonState) {
        when (state) {
            DEFAULT -> {
                isPressed = false
                isCorrect = false
                isIncorrect = false
                isActivated = false
            }
            SELECTED -> {
                isPressed = true

                isCorrect = false
                isIncorrect = false
                isActivated = false
            }
            CORRECT -> {
                isCorrect = true
                isActivated = true

                isPressed = false
                isIncorrect = false
            }
            INCORRECT -> {
                isIncorrect = true
                isActivated = true

                isCorrect = false
                isPressed = false
            }
        }
    }

    companion object {
        private val WORD_BOX_STATUS_CORRECT: IntArray = intArrayOf(R.attr.state_correct)
        private val WORD_BOX_STATUS_INCORRECT: IntArray = intArrayOf(R.attr.state_incorrect)
    }
}