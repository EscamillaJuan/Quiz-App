package com.app.service

import android.widget.Button
import android.widget.TextView
import com.app.model.GameQuestion

interface IGameService {
    fun shuffleQuestions(): List<GameQuestion>
    fun nextQuestion(index: Int, questions: List<GameQuestion>): Int
    fun prevQuestions(index: Int, questions: List<GameQuestion>): Int
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