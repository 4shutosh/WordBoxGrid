package com.task.wordament.gridView

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.wordament.wordBox.WordBoxButton
import com.task.wordament.wordBox.WordButtonData

class WordBoxAdapter : ListAdapter<WordButtonData, WordBoxHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordBoxHolder {
        val view = WordBoxButton(parent.context, null, 0)
        return WordBoxHolder(view)
    }

    override fun onBindViewHolder(holder: WordBoxHolder, position: Int) {
        val item = getItem(position)
        holder.wordBoxButton.setData(item)
        if (item.isSelected) {
            holder.wordBoxButton.setSelected()
        } else {
            holder.wordBoxButton.deselect()
        }
        if (item.isCorrect) holder.wordBoxButton.setCorrect()
        if (item.isInCorrect) holder.wordBoxButton.setInCorrect()
    }
}

class DiffCallBack : DiffUtil.ItemCallback<WordButtonData>() {
    override fun areItemsTheSame(oldItem: WordButtonData, newItem: WordButtonData) =
        oldItem.alphabet == newItem.alphabet && oldItem.score == newItem.score

    override fun areContentsTheSame(oldItem: WordButtonData, newItem: WordButtonData) =
        oldItem == newItem
}

class WordBoxHolder constructor(
    val wordBoxButton: WordBoxButton,
) : RecyclerView.ViewHolder(wordBoxButton)