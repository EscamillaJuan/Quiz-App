package com.app.model

import android.content.Context
import android.graphics.Color
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.btnRight
import com.app.btnWrong
import com.app.service.IGameService
import com.app.service.implementation.GameServiceImpl

class GameModel : ViewModel() {
    private val gameService: IGameService = GameServiceImpl()
    private val questions = gameService.shuffleQuestions()
    private var currentQuestionIndex: Int = 0
    private var hint: Int = 5
    private var sumCorrectAnswered: Int = 0
    private var sumIncorrectAnswered: Int = 0
    private var hintUsedCounter: Int = 0

    val currentHintText: String
        get() = "$hint pistas"
    val currentQuestionOptions: List<String>
        get() = questions[currentQuestionIndex].answerOptions
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
        hintUsedCounter = 0    // flag reset
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

    fun checkAnswer(
        optionBtn: List<Button>,
        currentOptionBtn: Int,
        optionText: CharSequence,
        context: Context,
    ) {
        currentQuestionIsAnswered = true
        var response = "Incorrecto"
        if (optionText.toString() == currentQuestionAnswer) {
            response = "Correcto"
            currentQuestionIsCorrect = true
            optionBtn[currentOptionBtn].setBackgroundColor(btnRight)
            sumCorrectAnswered++
        }
        optionBtn[currentOptionBtn].setBackgroundColor(btnWrong)
        sumIncorrectAnswered++
        hintUsedCounter++
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
    }

    fun currentHint(context: Context): Int {
        hint -= 1
        if (hint < 0) {
            hint = 0
            Toast.makeText(context, "No hay más pistas", Toast.LENGTH_SHORT).show()
        }
        return hint
    }

    fun checkHint(optionBtn: List<Button>, mode: String, context: Context) {
        when (mode) {
            "easy" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (hintUsedCounter == 1) {
                        if (currentQuestionAnswer == optionBtn[i].text)
                            optionBtn[i].setBackgroundColor(btnRight)
                    }
                    if(hintUsedCounter > 1) Toast.makeText(context, "todo quieres", Toast.LENGTH_SHORT).show()
                }
            }

            "medium" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text) {
                        optionBtn[0].setBackgroundColor(btnWrong)

                    }
                    if (sumIncorrectAnswered == 2) {
                        if (currentQuestionAnswer == optionBtn[i].text) optionBtn[i].setBackgroundColor(
                            btnRight
                        )
                    }
                    if (hintUsedCounter > 2){
                        Toast.makeText(context, "todo quieres", Toast.LENGTH_SHORT).show()

                    }
                }
            }

            "hard" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text && hintUsedCounter < 3) {
                        optionBtn[sumIncorrectAnswered].setBackgroundColor(btnWrong)
                    }
                    if (hintUsedCounter == 3 && currentQuestionAnswer == optionBtn[i].text) {
                        optionBtn[i].setBackgroundColor(btnRight)
                    }
                    if (hintUsedCounter>3){
                        Toast.makeText(context, "xd", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }
}