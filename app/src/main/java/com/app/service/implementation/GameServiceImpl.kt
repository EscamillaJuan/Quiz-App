package com.app.service.implementation

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
        options: List<Map<String, Boolean>>,
        answer: Map<String, Boolean>
    ): List<Map<String, Boolean>> {
        var answers: MutableList<Map<String, Boolean>> = mutableListOf()
        when (mode) {
            "easy" -> answers.addAll(options.subList(0, 1))
            "medium" -> answers.addAll(options.subList(0, 2))
            else -> answers.addAll(options)
        }
        answers.add(answer)
        return answers.shuffled()
    }
}