package com.task.wordament.wordBox

data class WordButtonData(
    val alphabet: String,
    val score: Int = 10,
    var state: WordBoxButtonState = WordBoxButtonState.DEFAULT

)

enum class WordBoxButtonState { DEFAULT, SELECTED, CORRECT, INCORRECT }