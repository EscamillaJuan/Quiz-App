package com.app.model

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.service.GameService
import com.app.service.implementation.GameServiceImpl

class GameModel : ViewModel() {
    private val gameService: GameService = GameServiceImpl()
    private val questions = gameService.shuffleQuestions()
    private var currentQuestionIndex: Int = 0

    val currentQuestionText: String
        get() = questions[currentQuestionIndex].text

    val currentQuestionAnswer: String
        get() = questions[currentQuestionIndex].correctAnswer
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

    fun getOptions(mode: String, optionBtn: List<Button>) {
        gameService.getOptions(
            mode,
            questions[currentQuestionIndex].answerOptions,
            questions[currentQuestionIndex].correctAnswer,
            optionBtn
        )
    }
    fun checkAnswer(option: CharSequence, context: Context) {
        val response = if(option.toString() == currentQuestionAnswer) "Correcto" else "Incorrecto"
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
    }
}