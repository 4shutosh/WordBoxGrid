package com.task.wordament.wordBox

data class WordButtonData(
    val alphabet: String,
    val score: Int = 10,
    var isSelected: Boolean = false,
    var isCorrect: Boolean = false,
    var isInCorrect: Boolean = false
)