package com.app.model.entity

data class Question (
    override val text: String,
    override val topic: String,
    override val topicIcon: Int,
    override val answerOptions: List<String>,
    override val correctAnswer: String,
) : IQuestion