package com.app.model

import android.content.Context
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.btnRight
import com.app.btnWrong
import com.app.service.IGameService
import com.app.service.implementation.GameServiceImpl

class GameModel : ViewModel() {
    private val gameService: IGameService = GameServiceImpl()
    private var questions = gameService.shuffleQuestions()
    private var currentQuestionIndex: Int = 0
    private var hint: Int = 5
    private var sumCorrectAnswered: Int = 0
    private var sumIncorrectAnswered: Int = 0
    private var hintUsedCounter: Int = 0
    private var questionCounter = 0
    private var usedHintsPerGame: Int = 0
    private var score = 0

    val totalScore
        get() = score
    val usedHintsCounter
        get() = usedHintsPerGame
    val answeredQuestionCounter
        get() = questionCounter
    val unusedHintsCounter
        get() = hint
    val correctAnswersCounter
        get() = sumCorrectAnswered
    val currentHintText: String
        get() = "$hint pistas"
    val currentQuestionOptions: List<String>
        get() = questions[currentQuestionIndex].answerOptions
    val currentQuestionIsAnswered: Boolean
        get() = questions[currentQuestionIndex].isAnswered

    val currentQuestionIsCorrect: Boolean
        get() = questions[currentQuestionIndex].isCorrect

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
        hintUsedCounter = 0
    }

    fun prevQuestion(mode: String, optionBtn: List<Button>) {
        currentQuestionIndex = gameService.prevQuestions(currentQuestionIndex, questions)
        getOptions(mode, optionBtn)
        hintUsedCounter = 0
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
        questionCounter++
        if (optionText.toString() == currentQuestionAnswer) {
            questions[currentQuestionIndex].isCorrect = true
            optionBtn[currentOptionBtn].setBackgroundColor(btnRight)
            sumCorrectAnswered++
        } else {
            optionBtn[currentOptionBtn].setBackgroundColor(btnWrong)
            sumIncorrectAnswered++
            hintUsedCounter++
        }
    }

    fun currentHint(context: Context): Int {
        hint -= 1
        if (hint < 0) {
            hint = 0
            Toast.makeText(context, "No hay más pistas", Toast.LENGTH_SHORT).show()
        }
        return hint
    }

    fun checkHint(
        optionBtn: List<Button>,
        mode: String,
        context: Context,
        textAnsweredQuestion: TextView
    ) {
        usedHintsPerGame += 1
        when (mode) {
            "easy" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (hintUsedCounter == 1) {
                        if (currentQuestionAnswer == optionBtn[i].text) {
                            optionBtn[i].setBackgroundColor(btnRight)
                            questions[currentQuestionIndex].isAnswered = true
                            questions[currentQuestionIndex].isCorrect = true
                            questionCounter++
                            scoreCounter(mode)
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
                        "todo quieres",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            "medium" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text && hintUsedCounter < 2) {
                        optionBtn[hintUsedCounter].setBackgroundColor(btnWrong)
                    }
                    if (hintUsedCounter == 2) {
                        if (currentQuestionAnswer == optionBtn[i].text) {
                            optionBtn[i].setBackgroundColor(btnRight)
                            questions[currentQuestionIndex].isAnswered = true
                            questions[currentQuestionIndex].isCorrect = true
                            questionCounter++
                            scoreCounter(mode)
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
                        Toast.makeText(context, "todo quieres", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            "hard" -> {
                hintUsedCounter++
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text && hintUsedCounter < 3) {
                        optionBtn[hintUsedCounter].setBackgroundColor(btnWrong)
                    }
                    if (hintUsedCounter == 3 && optionBtn[i].text == currentQuestionAnswer) {
                        optionBtn[i].setBackgroundColor(btnRight)
                        questions[currentQuestionIndex].isAnswered = true
                        questions[currentQuestionIndex].isCorrect = true
                        questionCounter++
                        scoreCounter(mode)
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
                        Toast.makeText(context, "xd", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }

    fun scoreCounter(mode: String) {
        if (!currentQuestionIsCorrect) return
        score = gameService.scoreCounter(mode, hintUsedCounter, score)
    }
}