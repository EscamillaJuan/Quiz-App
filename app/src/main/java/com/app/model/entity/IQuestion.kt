package com.app.model.entity

interface IQuestion {
    val text: String
    val topic: String
    val topicIcon: Int
    val answerOptions: List<String>
    val correctAnswer: String
}