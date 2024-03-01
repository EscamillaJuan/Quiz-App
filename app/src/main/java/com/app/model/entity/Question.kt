package com.app.model.entity

data class Question(
    val text: String,
    val topic: String,
    val topicIcon: Int,
    val answerOptions: List<String>,
    val correctAnswer: String,
    val answered: Boolean
)