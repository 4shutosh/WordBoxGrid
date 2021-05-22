package com.task.wordament.gridView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.task.wordament.databinding.WordButtonGridBinding
import com.task.wordament.trie.Trie
import com.task.wordament.wordBox.WordButtonData

/*WordBoxGrid : a custom view using WordBoxButton, observe the values to selectedString & selectedScore
to get the dragged selected alphabet sequence: String & corresponding score:Int
to populate the view with alphabets, scores use function setData() : List<WordButtonData>
to set the correct alphabets for the game, use function setCorrectWords() : List<String>
*/

class WordBoxGrid @JvmOverloads constructor(
    context: Context,
    attribute: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attribute, defStyleAttr) {

    private var binding: WordButtonGridBinding
    private var boxAdapter: WordBoxAdapter
    private lateinit var dataList: List<WordButtonData>

    private lateinit var correctStringTrie: Trie<Char>

    private var selectedIndices = arrayListOf<Int>()

    private var _selectedString: MutableLiveData<String> = MutableLiveData()
    val selectedString: LiveData<String> get() = _selectedString

    private var _selectedScore: MutableLiveData<Int> = MutableLiveData()
    val selectedScore: LiveData<Int> get() = _selectedScore

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = WordButtonGridBinding.inflate(inflater, this, true)

        val gridManager = GridLayoutManager(context, 4)
        boxAdapter = WordBoxAdapter()

        val receiver = object : WordBoxGridReceiver {
            override fun setSelected(index: Int, selected: Boolean) {
                selectItem(index, selected)
            }

            override fun actionRelease(list: ArrayList<Int>) {
                // get the list here
                if (list.isNotEmpty()) {
                    // save string here
                    selectedIndices.clear()
                    selectedIndices.addAll(list)
                    appendAndCalculate()
                }
            }

            override fun isSelected(index: Int): Boolean {
                return dataList[index].isSelected
            }

        }
        val listener = WordBoxGridTouchListener(receiver)

        val itemDecoration = WordBoxGridDecoration(10)

        binding.recycler.apply {
            layoutManager = gridManager
            adapter = boxAdapter
            addOnItemTouchListener(listener)
            addItemDecoration(itemDecoration)
        }
    }

    fun setData(list: List<WordButtonData>) {
        dataList = list
        boxAdapter.submitList(list)
    }

    fun setCorrectWords(correctString: List<String>) {
        correctStringTrie = Trie()
        correctString.forEach {
            // inserting each letter, further character wise in trie
            correctStringTrie.insert(it)
        }
    }

    // set correct to the buttons of rView using the index provided
    private fun selectItem(index: Int, selected: Boolean) {
        if (dataList[index].isSelected != selected) {
            dataList[index].isSelected = selected
            boxAdapter.notifyItemChanged(index)
        }
    }

    private fun appendAndCalculate() {
        var selectedStringTemp = ""
        var selectedScoreTemp = 0
        for (index in selectedIndices) {
            val alphabet = dataList[index].alphabet
            selectedStringTemp += alphabet

            val score = dataList[index].score
            selectedScoreTemp += score
        }
        _selectedString.value = selectedStringTemp
        _selectedScore.value = selectedScoreTemp
        examineStrings(correctStringTrie)
    }

    // ?ask enabled disable touch when the result is getting calculated
    private fun examineStrings(charTrie: Trie<Char>) {
        if (charTrie.contains(selectedString.value.toString())) {
            // make them green
            for (index in selectedIndices) {
                dataList[index].isCorrect = true
                boxAdapter.notifyItemChanged(index)
            }
        } else {
            // make them red
            for (index in selectedIndices) {
                dataList[index].isInCorrect = true
                boxAdapter.notifyItemChanged(index)
            }
        }
        // delay of 1 sec to reset the selections & compared results
        handler.postDelayed({
            resetSelections()
        }, 1000)
    }

    private fun resetSelections() {
        for (index in selectedIndices) {
            dataList[index].isSelected = false
            dataList[index].isInCorrect = false
            dataList[index].isCorrect = false
            boxAdapter.notifyItemChanged(index)
        }
        // can clear selectedIndices here
    }
}

// easy implementation of the insert function
internal fun Trie<Char>.insert(string: String) {
    insert(string.toList())
}

// easy implementation of the contains function
internal fun Trie<Char>.contains(string: String): Boolean {
    return contains(string.toList())
}