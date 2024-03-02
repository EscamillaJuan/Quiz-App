package com.app.model

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.service.IGameService
import com.app.service.implementation.GameServiceImpl

class GameModel : ViewModel() {
    private val gameService: IGameService = GameServiceImpl()
    private val questions = gameService.shuffleQuestions()
    private var currentQuestionIndex: Int = 0

    val currentQuestionOptions: List<String>
        get() =questions[currentQuestionIndex].answerOptions
    var currentQuestionIsAnswered: Boolean
        get() = questions[currentQuestionIndex].isAnswered
        set(value) {
            questions[currentQuestionIndex].isAnswered = value
        }
    var currentQuestionIsCorrect: Boolean
        get() = questions[currentQuestionIndex].isCorrect
        set(value) {
            questions[currentQuestionIndex].isCorrect = value
        }
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

    fun nextQuestion(mode: String, optionBtn: List<Button>) {
        currentQuestionIndex = gameService.nextQuestion(currentQuestionIndex, questions)
        getOptions(mode, optionBtn)
    }

    fun prevQuestion(mode: String, optionBtn: List<Button>) {
        currentQuestionIndex = gameService.prevQuestions(currentQuestionIndex, questions)
        getOptions(mode, optionBtn)
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
        currentQuestionIsAnswered = true
        var response = "Incorrecto"
        if (option.toString() == currentQuestionAnswer) {
            response = "Correcto"
            currentQuestionIsCorrect = true
        }
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
    }
}