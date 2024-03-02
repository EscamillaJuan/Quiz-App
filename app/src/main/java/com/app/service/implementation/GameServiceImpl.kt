package com.app.service.implementation

import android.graphics.Color
import android.view.View
import android.widget.Button
import com.app.model.GameQuestion
import com.app.model.QuestionListProvider
import com.app.model.entity.Question
import com.app.service.IGameService

class GameServiceImpl : IGameService {
    private val questions = QuestionListProvider.items

    override fun shuffleQuestions(): List<GameQuestion> {
        return questions.shuffled().subList(0, 10).map { question: Question ->
            GameQuestion(
                text = question.text,
                topic = question.topic,
                topicIcon = question.topicIcon,
                answerOptions = question.answerOptions,
                correctAnswer = question.correctAnswer,
                isAnswered = false,
                isCorrect = false
            )
        }
    }

    override fun nextQuestion(index: Int, questions: List<GameQuestion>): Int {
        return (index + 1) % questions.size
    }

    override fun prevQuestions(index: Int, questions: List<GameQuestion>): Int {
        return (index - 1 + questions.size) % questions.size
    }

    override fun getOptions(
        mode: String,
        options: List<String>,
        answer: String,
        optionBtn: List<Button>
    ) {
        var answerOptions = mutableListOf<String>()
        when (mode) {
            "easy" -> {
                answerOptions.addAll(options.subList(0, 1))
                answerOptions.add(answer)
                answerOptions = answerOptions.shuffled().toMutableList()
                for (i in 0 until 2) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
                for (i in 2 until 4) {
                    optionBtn[i].visibility = View.GONE
                }
            }

            "medium" -> {
                answerOptions.addAll(options.subList(0, 2))
                answerOptions.add(answer)
                answerOptions = answerOptions.shuffled().toMutableList()
                for (i in 0 until 3) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
                optionBtn[3].visibility = View.GONE
            }

            "hard" -> {
                answerOptions.addAll(options)
                answerOptions.add(answer)
                answerOptions = answerOptions.shuffled().toMutableList()
                for (i in 0 until 4) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions.shuffled()[i]
                }
            }
        }
    }

    override fun setUserAnswer(
        isAnswered: Boolean,
        isCorrect: Boolean,
        optionBtn: List<Button>,
        currentQuestionAnswer: String,
        options: List<String>
    ) {
        for (i in optionBtn.indices) {
            if (isAnswered) {
                optionBtn[i].setBackgroundColor(
                    if (optionBtn[i].text.toString() == currentQuestionAnswer) Color.GREEN else Color.RED
                )
            } else {
                optionBtn[i].setBackgroundColor(Color.BLUE)
            }
        }
    }
}
