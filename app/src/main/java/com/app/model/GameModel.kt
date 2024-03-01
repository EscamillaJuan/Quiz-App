package com.app.model

import androidx.lifecycle.ViewModel
import com.app.service.GameService
import com.app.service.implementation.GameServiceImpl

class GameModel : ViewModel() {
    private val gameService: GameService = GameServiceImpl()
    private val questions = gameService.shuffleQuestions()
    private var currentQuestionIndex: Int = 0


    val currentQuestionText: String
        get() = questions[currentQuestionIndex].text
    val counterText: String
        get() = "Pregunta ${currentQuestionIndex + 1} de ${questions.size}"

    val topicText: String
        get() = questions[currentQuestionIndex].topic

    val topicIcon: Int
        get() = questions[currentQuestionIndex].topicIcon


    fun nextQuestion() {
        currentQuestionIndex = gameService.nextQuestion(currentQuestionIndex, questions)
    }

    fun prevQuestion() {
        currentQuestionIndex = gameService.prevQuestions(currentQuestionIndex, questions)
    }

    fun getOptions(mode: String): List<Map<String, Boolean>> {
        return gameService.getOptions(
            mode,
            questions[currentQuestionIndex].answerOptions,
            questions[currentQuestionIndex].correctAnswer
        )
    }
}