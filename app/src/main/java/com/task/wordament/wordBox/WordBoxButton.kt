package com.task.wordament.wordBox

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.task.wordament.R
import com.task.wordament.databinding.ItemWordButtonBinding

class WordBoxButton @JvmOverloads constructor(
    context: Context,
    attribute: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attribute, defStyleAttr) {

    // todo make text size dynamic

    private var binding: ItemWordButtonBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ItemWordButtonBinding.inflate(inflater, this, true)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    fun setData(data: WordButtonData) {
        binding.data = data
    }

    fun setCorrect() {
        binding.rootFrameLayout.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_square_correct))
        binding.alphabet.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.scoreText.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_word_score_selected))
    }

    fun setInCorrect() {
        binding.rootFrameLayout.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_square_incorrect))
        binding.alphabet.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.scoreText.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_word_score_selected))
    }

    fun setSelected() {
        // make it yellow
        binding.rootFrameLayout.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_square_selected))
        binding.alphabet.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.scoreText.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_word_score_selected))
    }

    fun deselect() {
        // make it white again
        binding.rootFrameLayout.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_square_unselected))
        binding.alphabet.setTextColor(ContextCompat.getColor(context, R.color.clementine))
        binding.scoreText.background =
            (ContextCompat.getDrawable(context, R.drawable.dr_word_score_unselected))
    }
}