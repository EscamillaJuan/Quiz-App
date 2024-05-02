package com.app.model

import android.content.Context
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.btnRight
import com.app.btnWrong
import com.app.database.AppDatabase
import com.app.service.IGameService
import com.app.service.implementation.GameServiceImpl

class GameModel(db: AppDatabase, newGame: Boolean) : ViewModel() {
    private val gameSessionQuestionDao = db.gameSessionQuestionsDao()
    private val gameService: IGameService = GameServiceImpl()
    private val questions = gameService.getGameQuestions(db, newGame)
    private var answerOptions = mutableListOf<List<String>>()
    private var currentQuestionIndex: Int = 0
    var hint: Int = 3
        set(value) {
            field = if (value >= 0) value else 3
        }
    private var sumCorrectAnswered: Int = 0
    private var sumIncorrectAnswered: Int = 0
    private var hintUsedCounter: Int = 0
    var questionCounter = 0
        set(value) {
            field = if(value >= 0) value else 0
        }
    private var usedHintsPerGame: Int = 0
    var score = 0
        set(value) {
            field = if(value >= 0) value else 0
        }
    private var prevAnswerIsCorrect: Boolean = false
    private var currentAnswerIsCorrect: Boolean = false
    private var currentAnsweredWithHint: Boolean = false
    private val correctQuestionIndex = mutableListOf<Int>()
    private var incorrectButtonIndex = mutableListOf<Int>()

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

    fun getAnswerOptions(mode: String) {
        for (i in questions.indices) {
            val answers =
                gameService.getAnswers(mode, questions[i].answerOptions, questions[i].correctAnswer)
            answerOptions.add(answers)
        }
    }

    fun nextQuestion(mode: String, optionBtn: List<Button>) {
        currentQuestionIndex = gameService.nextQuestion(currentQuestionIndex, questions)
        getOptions(mode, optionBtn)
        hintUsedCounter = 0
        incorrectButtonIndex.clear()
    }

    fun prevQuestion(mode: String, optionBtn: List<Button>) {
        currentQuestionIndex = gameService.prevQuestions(currentQuestionIndex, questions)
        getOptions(mode, optionBtn)
        hintUsedCounter = 0
        incorrectButtonIndex.clear()
    }

    fun getOptions(mode: String, optionBtn: List<Button>) {
        gameService.getOptions(
            mode,
            answerOptions[currentQuestionIndex],
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
        val isCorrect = optionText.toString() == currentQuestionAnswer
        val question = gameSessionQuestionDao.getGameSessionQuestion(currentQuestionIndex)
        gameSessionQuestionDao.updateGameQuestions(
            question.copy(
                isCorrect = isCorrect,
                isAnswered = true,
            )
        )

        if (isCorrect) {
            if (hintUsedCounter == 0) {
                currentAnsweredWithHint = false
            }
            if (!currentAnsweredWithHint) {
                correctQuestionIndex.add(currentQuestionIndex)
                if (correctQuestionIndex.size == 2) {
                    prevAnswerIsCorrect =
                        questions[correctQuestionIndex[0]].isAnswered && questions[correctQuestionIndex[0]].isCorrect
                }
            }
            questions[currentQuestionIndex].isCorrect = true
            optionBtn[currentOptionBtn].setBackgroundColor(btnRight)
            currentAnswerIsCorrect = true
            sumCorrectAnswered++
        } else {
            optionBtn[currentOptionBtn].setBackgroundColor(btnWrong)
            sumIncorrectAnswered++
            currentAnswerIsCorrect = false
            hintUsedCounter++
        }
    }

    fun extraHint(context: Context): Int {
        if (currentAnswerIsCorrect && prevAnswerIsCorrect) {
            hint++
            currentAnswerIsCorrect = false
            prevAnswerIsCorrect = false
            currentAnsweredWithHint = true
            correctQuestionIndex.clear()
            if (hint > 5) {
                hint = 5
                Toast.makeText(context, "No hay más pistas extras disponibles", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Tiene una pista extra", Toast.LENGTH_SHORT).show()
            }

        }
        return hint
    }

    fun currentHint(context: Context): Int {
        hint--
        if (hint < 0) {
            Toast.makeText(context, "No hay más pistas disponibles", Toast.LENGTH_SHORT).show()
            hint = 0
        }
        return hint
    }

    fun incorrectButtonIndexList(
        optionBtn: List<Button>,
        isShuffled: Boolean,
        mode: String
    ): Boolean {
        if (!isShuffled) {
            when (mode) {
                "medium" -> {
                    for (i in 0 until 3) {
                        if (currentQuestionAnswer != optionBtn[i].text) {
                            incorrectButtonIndex.add(i)
                            incorrectButtonIndex = incorrectButtonIndex.shuffled().toMutableList()
                        }
                    }
                }

                "hard" -> {
                    for (i in optionBtn.indices) {
                        if (currentQuestionAnswer != optionBtn[i].text) {
                            incorrectButtonIndex.add(i)
                            incorrectButtonIndex = incorrectButtonIndex.shuffled().toMutableList()
                        }
                    }
                }
            }
        }
        return isShuffled
    }

    fun checkHint(
        optionBtn: List<Button>,
        mode: String,
        context: Context,
        textAnsweredQuestion: TextView
    ) {
        hintUsedCounter++
        usedHintsPerGame += 1
        when (mode) {
            "easy" -> {
                for (i in optionBtn.indices) {
                    if (hintUsedCounter == 1) {
                        if (currentQuestionAnswer == optionBtn[i].text) {
                            optionBtn[i].setBackgroundColor(btnRight)
                            questions[currentQuestionIndex].isAnswered = true
                            questions[currentQuestionIndex].isCorrect = true
                            prevAnswerIsCorrect = false
                            currentAnsweredWithHint = true
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
                            if (questionCounter > 9) optionBtn[i].performClick()
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
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text && hintUsedCounter < 2) {
                        optionBtn[incorrectButtonIndex[hintUsedCounter - 1]].setBackgroundColor(
                            btnWrong
                        )
                        currentAnsweredWithHint = true
                    } else if (currentQuestionAnswer == optionBtn[i].text && hintUsedCounter == 2) {
                        optionBtn[i].setBackgroundColor(btnRight)
                        questions[currentQuestionIndex].isAnswered = true
                        questions[currentQuestionIndex].isCorrect = true
                        currentAnsweredWithHint = true
                        prevAnswerIsCorrect = false
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
                        if (questionCounter > 9) optionBtn[i].performClick()
                    } else if (hintUsedCounter > 2) {
                        Toast.makeText(context, "pista no disponible", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            "hard" -> {
                for (i in optionBtn.indices) {
                    if (currentQuestionAnswer != optionBtn[i].text && hintUsedCounter < 3) {
                        optionBtn[incorrectButtonIndex[hintUsedCounter - 1]].setBackgroundColor(
                            btnWrong
                        )
                        currentAnsweredWithHint = true
                    } else if (hintUsedCounter == 3 && optionBtn[i].text == currentQuestionAnswer) {
                        optionBtn[i].setBackgroundColor(btnRight)
                        questions[currentQuestionIndex].isAnswered = true
                        questions[currentQuestionIndex].isCorrect = true
                        currentAnsweredWithHint = true
                        prevAnswerIsCorrect = false
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
                        if (questionCounter > 9) optionBtn[i].performClick()
                    } else if (hintUsedCounter > 3) {
                        Toast.makeText(context, "Pista no disponible", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        // incorrectButtonIndex.clear()

    }

    fun scoreCounter(mode: String) {
        if (!currentQuestionIsCorrect) return
        score = gameService.scoreCounter(mode, hintUsedCounter, score)
    }
}