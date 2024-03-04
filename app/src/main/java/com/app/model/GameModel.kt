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
    private var hint: Int = 5
    private var sumCorrectAnswered: Int = 0
    private var sumIncorrectAnswered: Int = 0
    private var hintUsedCounter: Int = 0
    private var prevAnswerIsCorrect: Boolean = false
    private var currentAnswerIsCorrect: Boolean = false
    private var currentAnsweredWithHint: Boolean = false



    val currentQuestionText: String
        get() = questions[currentQuestionIndex].text

    private val currentQuestionAnswer: String
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

    fun checkAnswer(
        optionBtn: List<Button>,
        currentOptionBtn: Int,
        optionText: CharSequence,
    ) {
        questions[currentQuestionIndex].isAnswered = true

        if (optionText.toString() == currentQuestionAnswer) {
            if (currentQuestionIndex > 0 && !currentAnsweredWithHint) {
            prevAnswerIsCorrect =
                questions[currentQuestionIndex - 1].isAnswered && questions[currentQuestionIndex - 1].isCorrect
            }
            questions[currentQuestionIndex].isCorrect = true
            optionBtn[currentOptionBtn].setBackgroundColor(btnRight)
            currentAnswerIsCorrect  = true
            if (hintUsedCounter==0){currentAnsweredWithHint = false}

            sumCorrectAnswered++

        } else {
            optionBtn[currentOptionBtn].setBackgroundColor(btnWrong)
            sumIncorrectAnswered++
            currentAnswerIsCorrect  = false

        }

    }
    fun extraHint(context: Context):Int {
        if (currentAnswerIsCorrect && prevAnswerIsCorrect) {
            hint++
            currentAnswerIsCorrect = false
            prevAnswerIsCorrect = false
            currentAnsweredWithHint = true
            if (hint > 5) {
                hint = 5
                Toast.makeText(context, "No hay más pistas extras disponibles", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Tiene una pista extra", Toast.LENGTH_SHORT).show()
            }

        }

        return hint
    }

    fun currentHint(context: Context): Int {
        hint--
        if (hint < 0) {
            hint = 0
            Toast.makeText(context, "No hay más pistas disponibles", Toast.LENGTH_SHORT).show()
        }
        return hint
    }

    fun checkHint(optionBtn: List<Button>, mode: String, context: Context, textAnsweredQuestion: TextView) {
        when (mode) {
            "easy" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (hintUsedCounter == 1) {
                        if (currentQuestionAnswer == optionBtn[i].text) {
                            optionBtn[i].setBackgroundColor(btnRight)
                            questions[currentQuestionIndex].isAnswered = true
                            questions[currentQuestionIndex].isCorrect = true
                            prevAnswerIsCorrect = false
                            currentAnsweredWithHint = true
                            gameService.setUserAnswer(
                                currentQuestionIsAnswered,
                                currentQuestionIsCorrect,
                                optionBtn,
                                currentQuestionAnswer,
                                currentQuestionOptions,
                                textAnsweredQuestion
                            )
                        }
                    }
                    if (hintUsedCounter > 1) Toast.makeText(
                        context,
                        "Pista no disponible",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            "medium" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text && hintUsedCounter < 2) {
                        optionBtn[hintUsedCounter].setBackgroundColor(btnWrong)
                        currentAnsweredWithHint = true
                    }
                    if (hintUsedCounter == 2) {
                        if (currentQuestionAnswer == optionBtn[i].text) {
                            optionBtn[i].setBackgroundColor(btnRight)
                            questions[currentQuestionIndex].isAnswered = true
                            questions[currentQuestionIndex].isCorrect = true
                            currentAnsweredWithHint = true
                            prevAnswerIsCorrect = false
                            gameService.setUserAnswer(
                                currentQuestionIsAnswered,
                                currentQuestionIsCorrect,
                                optionBtn,
                                currentQuestionAnswer,
                                currentQuestionOptions,
                                textAnsweredQuestion
                            )
                        }
                    }
                    if (hintUsedCounter > 2) {
                        Toast.makeText(context, "Pista no disponible", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            "hard" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text && hintUsedCounter < 3) {
                        optionBtn[hintUsedCounter].setBackgroundColor(btnWrong)
                        currentAnsweredWithHint = true
                    }
                    if (hintUsedCounter == 3 && optionBtn[i].text == currentQuestionAnswer) {
                        optionBtn[i].setBackgroundColor(btnRight)
                        questions[currentQuestionIndex].isAnswered = true
                        questions[currentQuestionIndex].isCorrect = true
                        currentAnsweredWithHint = true
                        prevAnswerIsCorrect = false
                        gameService.setUserAnswer(
                            currentQuestionIsAnswered,
                            currentQuestionIsCorrect,
                            optionBtn,
                            currentQuestionAnswer,
                            currentQuestionOptions,
                            textAnsweredQuestion
                        )
                    }
                    if (hintUsedCounter > 3) {
                        Toast.makeText(context, "Pista no disponible", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }
}