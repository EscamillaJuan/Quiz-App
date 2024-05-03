package com.app.service

import android.widget.Button
import android.widget.TextView
import com.app.database.AppDatabase
import com.app.utils.GameQuestionModel

interface IGameService {
    fun getGameQuestions(db: AppDatabase, newGame: Boolean): List<GameQuestionModel>
    fun nextQuestion(index: Int, questions: List<GameQuestionModel>): Int
    fun prevQuestions(index: Int, questions: List<GameQuestionModel>): Int
    fun getOptions(
        mode: String,
        answerOptions: List<String>,
        optionBtn: List<Button>
    )

    fun setUserAnswer(
        isAnswered: Boolean,
        isCorrect: Boolean,
        optionBtn: List<Button>,
        currentQuestionAnswer: String,
        options: List<String>,
        textAnsweredQuestion: TextView
    )

    fun scoreCounter(mode: String, usedHints: Int, score: Int): Int
    fun getAnswers(mode: String, options: List<String>, answer: String): List<String>
}