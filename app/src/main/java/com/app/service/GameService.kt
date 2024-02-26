package com.app.service

import com.app.model.entity.Question

interface GameService {
    fun shuffleQuestions(): List<Question>
    fun nextQuestion(index: Int, questions: List<Question>): Int
    fun prevQuestions(index: Int, questions: List<Question>): Int
    fun getOptions(
        mode: String,
        options: List<Map<String, Boolean>>,
        answer: Map<String, Boolean>
    ): List<Map<String, Boolean>>
}