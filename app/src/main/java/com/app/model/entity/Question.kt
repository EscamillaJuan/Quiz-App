package com.app.model.entity

data class Question(
    val text: String,
    val topic: String,
    val topicIcon: Int,
    val answerOptions: List<Map<String, Boolean>>,
    val correctAnswer: Map<String, Boolean>
)