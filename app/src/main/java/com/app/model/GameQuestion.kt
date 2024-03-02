package com.app.model

import com.app.model.entity.IQuestion

data class GameQuestion (
    override val text: String,
    override val topic: String,
    override val topicIcon: Int,
    override val answerOptions: List<String>,
    override val correctAnswer: String,
    val isAnswered: Boolean,
    val isCorrect: Boolean
) : IQuestion