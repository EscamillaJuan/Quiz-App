package com.app.model

data class Question (
    val text: String,
    val topic: String,
    val answerOptions: Map<String, Boolean>,
    val correctAnswer: Map<String, Boolean>
)