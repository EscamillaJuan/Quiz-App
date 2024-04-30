package com.app.utils

data class GameQuestionModel(
    val id: Int,
    val text: String,
    val topic: String,
    val topicIcon: Int,
    val answerOptions: List<String>,
    val correctAnswer: String,
    var isAnswered: Boolean,
    var isCorrect: Boolean
)