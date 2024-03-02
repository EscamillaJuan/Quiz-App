package com.app.service

import android.widget.Button
import com.app.model.GameQuestion

interface IGameService {
    fun shuffleQuestions(): List<GameQuestion>
    fun nextQuestion(index: Int, questions: List<GameQuestion>): Int
    fun prevQuestions(index: Int, questions: List<GameQuestion>): Int
    fun getOptions(
        mode: String,
        options: List<String>,
        answer: String,
        optionBtn: List<Button>
    )
}