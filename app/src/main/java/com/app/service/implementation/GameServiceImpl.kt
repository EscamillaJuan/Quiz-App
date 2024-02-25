package com.app.service.implementation

import com.app.model.QuestionListProvider
import com.app.model.entity.Question
import com.app.service.GameService

class GameServiceImpl : GameService {
    private val questions = QuestionListProvider.items

    override fun shuffleQuestions(): List<Question> {
        return questions.shuffled().subList(0, 9)
    }

    override fun nextQuestion(index: Int, questions: List<Question>): Int {
        return (index + 1) % questions.size
    }

    override fun prevQuestions(index: Int, questions: List<Question>): Int {
        return (index - 1 + questions.size) % questions.size
    }

}