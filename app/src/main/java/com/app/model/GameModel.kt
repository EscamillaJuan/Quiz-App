package com.app.model

import androidx.lifecycle.ViewModel
import com.app.model.entity.Question
import com.app.service.GameService
import com.app.service.implementation.GameServiceImpl

class GameModel: ViewModel() {
    private val gameService: GameService = GameServiceImpl()
    private val questions = gameService.shuffleQuestions()
    private var currentQuestionIndex: Int = 0

    val currentQuestionText: String
        get() = questions[currentQuestionIndex].text

    fun nextQuestion() {
        currentQuestionIndex = gameService.nextQuestion(currentQuestionIndex, questions)
    }

    fun prevQuestion() {
        currentQuestionIndex = gameService.prevQuestions(currentQuestionIndex, questions)
    }

}