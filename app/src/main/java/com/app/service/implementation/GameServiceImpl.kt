package com.app.service.implementation

import android.view.View
import android.widget.Button
import com.app.model.QuestionListProvider
import com.app.model.entity.Question
import com.app.service.GameService

class GameServiceImpl : GameService {
    private val questions = QuestionListProvider.items

    override fun shuffleQuestions(): List<Question> {
        return questions.shuffled().subList(0, 10)
    }

    override fun nextQuestion(index: Int, questions: List<Question>): Int {
        return (index + 1) % questions.size
    }

    override fun prevQuestions(index: Int, questions: List<Question>): Int {
        return (index - 1 + questions.size) % questions.size
    }

    override fun getOptions(
        mode: String,
        options: List<String>,
        answer: String,
        optionBtn: List<Button>
    ){
        val answerOptions = mutableListOf<String>()
        when (mode) {
            "easy" -> {
                answerOptions.addAll(options.shuffled().subList(0, 1))
                answerOptions.add(answer)
                for (i in 0 until 2) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
                for (i in 2 until 4) {
                    optionBtn[i].visibility = View.GONE
                }
            }
            "medium" -> {
                answerOptions.addAll(options.shuffled().subList(0, 2))
                answerOptions.add(answer)
                for (i in 0 until 3) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
                optionBtn[3].visibility = View.GONE
            }
            "hard" -> {
                answerOptions.addAll(options.shuffled())
                answerOptions.add(answer)
                for (i in 0 until 4) {
                    optionBtn[i].visibility = View.VISIBLE
                    optionBtn[i].text = answerOptions[i]
                }
            }
        }
    }
}
