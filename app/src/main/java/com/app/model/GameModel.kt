package com.app.model

import android.content.Context
import android.graphics.Color
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.service.GameService
import com.app.service.implementation.GameServiceImpl

val ROJO    = Color.parseColor("#CC0000")
val VERDE   = Color.parseColor("#99CC00")
val CAFE    = Color.parseColor("#624D1B")
class GameModel : ViewModel() {
    private val gameService: GameService = GameServiceImpl()
    private val questions = gameService.shuffleQuestions()
    private var currentQuestionIndex: Int = 0
    private var hint:Int = 5
    private var sumCorrectAnswered = 0
    private var sumIncorrectAnswered = 0
    private var currentIncorrectAnswered = 0


    val currentHintText: String
        get() = "$hint pistas"
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
        currentIncorrectAnswered = 0    // flag reset
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
    fun checkAnswer(optionBtn: List<Button>,currentOptionBtn: Int, optionText: CharSequence, context: Context,): Boolean {
        val response =
            if (optionText.toString() == currentQuestionAnswer) "Correcto" else "Incorrecto"
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()

            if(optionText.toString() == currentQuestionAnswer){
                optionBtn[currentOptionBtn].setBackgroundColor(com.app.view.VERDE)
                sumCorrectAnswered++
            }
            else{
                optionBtn[currentOptionBtn].setBackgroundColor(com.app.view.ROJO)
                sumIncorrectAnswered++
                currentIncorrectAnswered++
            }
        return optionText.toString() == currentQuestionAnswer
    }

    fun currentHint(context: Context): Int {
        hint -= 1
        if(hint < 0){
            hint = 0
            Toast.makeText(context,"No hay más pistas", Toast.LENGTH_SHORT).show()
        }
        return hint
    }
    fun checkHint(optionBtn: List<Button>, mode: String) {

        if (mode == "easy") {
            for (i in optionBtn.indices) {
                if (currentQuestionAnswer == optionBtn[i].text)
                    optionBtn[i].setBackgroundColor(VERDE)
            }
        }
        if (mode == "medium") {
            sumIncorrectAnswered++
            for (i in optionBtn.indices) {
                if (currentQuestionAnswer != optionBtn[i].text) {
                    optionBtn[0].setBackgroundColor(ROJO)
                    sumIncorrectAnswered

                }
                if (sumIncorrectAnswered == 2) {
                    if (currentQuestionAnswer == optionBtn[i].text) optionBtn[i].setBackgroundColor(
                        VERDE
                    )
                }
            }

        }
        if (mode == "hard") {
            sumIncorrectAnswered++
            for (i in optionBtn.indices) {
                if (currentQuestionAnswer != optionBtn[i].text) {
                    optionBtn[sumIncorrectAnswered].setBackgroundColor(ROJO)
                        if (sumIncorrectAnswered >= 2) sumIncorrectAnswered = 0

                }
                if (sumIncorrectAnswered == 3) {
                    if (currentQuestionAnswer == optionBtn[i].text)
                        optionBtn[i].setBackgroundColor(VERDE)
                }
            }
        }
    }
}