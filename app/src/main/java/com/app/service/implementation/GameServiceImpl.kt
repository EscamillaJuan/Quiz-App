package com.app.service.implementation

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.app.btnColor
import com.app.btnRight
import com.app.btnWrong
import com.app.database.entity.Topic
import com.app.utils.GameQuestionModel
import com.app.database.entity.Question
import com.app.service.IGameService

class GameServiceImpl : IGameService {
    override fun shuffleQuestions(topicWithQuestions: Map<Topic, List<Question>>): List<GameQuestionModel> {
        val gameQuestions = mutableListOf<GameQuestionModel>()
        topicWithQuestions.forEach { (topic, questions) ->
            questions.forEach { question ->
                gameQuestions.add(
                    GameQuestionModel(
                        id = question.id,
                        text = question.text,
                        topic = topic.title,
                        topicIcon = topic.icon,
                        answerOptions = listOf(
                            question.option1,
                            question.option2,
                            question.option3,
                        ),
                        correctAnswer = question.answer,
                        isAnswered = false,
                        isCorrect = false,
                    )
                )
            }
        }
        return gameQuestions
    }

    override fun nextQuestion(index: Int, questions: List<GameQuestionModel>): Int {
        return (index + 1) % questions.size
    }

    override fun prevQuestions(index: Int, questions: List<GameQuestionModel>): Int {
        return (index - 1 + questions.size) % questions.size
    }

    override fun getOptions(
        mode: String,
        answerOptions: List<String>,
        optionBtn: List<Button>
    ) {
        when (mode) {
            "easy" -> {
                for (i in 0 until 2) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
                for (i in 2 until 4) {
                    optionBtn[i].visibility = View.GONE
                }
            }

            "medium" -> {
                for (i in 0 until 3) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
                optionBtn[3].visibility = View.GONE
            }

            "hard" -> {
                for (i in 0 until 4) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
            }
        }
    }

    override fun setUserAnswer(
        isAnswered: Boolean,
        isCorrect: Boolean,
        optionBtn: List<Button>,
        currentQuestionAnswer: String,
        options: List<String>,
        textAnsweredQuestion: TextView
    ) {
        for (i in optionBtn.indices) {
            if (isAnswered) {
                optionBtn[i].isEnabled = false
                textAnsweredQuestion.visibility = View.VISIBLE
                textAnsweredQuestion.text =
                    if (isCorrect) "Tu respuesta fue correcta" else "Tu respuesta fue incorrecta"
                optionBtn[i].setBackgroundColor(
                    if (optionBtn[i].text.toString() == currentQuestionAnswer) btnRight else btnWrong
                )
            } else {
                optionBtn[i].isEnabled = true
                textAnsweredQuestion.visibility = View.GONE
                optionBtn[i].setBackgroundColor(btnColor)
            }
        }
    }

    override fun scoreCounter(mode: String, usedHints: Int, score: Int): Int {
        val points = when (mode) {
            "easy" -> 2 - usedHints
            "medium" -> 4 - usedHints
            "hard" -> 6 - usedHints
            else -> 2
        }
        return score + points
    }

    override fun getAnswers(
        mode: String,
        options: List<String>,
        answer: String
    ): List<String> {
        var newOptions = mutableListOf<String>()
        when (mode) {
            "easy" -> {
                newOptions = options.subList(0, 1).toMutableList()
                newOptions.add(answer)
            }

            "medium" -> {
                newOptions = options.subList(0, 2).toMutableList()
                newOptions.add(answer)
            }

            "hard" -> {
                newOptions = options.subList(0, 3).toMutableList()
                newOptions.add(answer)
            }
        }
        return newOptions.shuffled()
    }
}