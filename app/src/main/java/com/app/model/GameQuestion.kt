package com.app.model

import com.app.model.entity.IQuestion

data class GameQuestion (
    override val text: String,
    override val topic: String,
    override val topicIcon: Int,
    override val answerOptions: List<String>,
    override val correctAnswer: String,
    var isAnswered: Boolean,
    var isCorrect: Boolean
) : IQuestion