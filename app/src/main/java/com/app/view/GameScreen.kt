package com.app.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.R
import com.app.model.GameModel

class GameScreen : AppCompatActivity() {
    private val gameModel: GameModel by viewModels()
    private lateinit var nextBtn: ImageButton
    private lateinit var prevBtn: ImageButton
    private lateinit var hintBtn: Button
    private lateinit var questionText: TextView
    private lateinit var questionsCounter: TextView
    private lateinit var topicText: TextView
    private lateinit var topicIcon: ImageView
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)
        nextBtn = findViewById(R.id.next_btn)
        prevBtn = findViewById(R.id.prev_btn)
        questionText = findViewById(R.id.question)
        questionText.text = gameModel.currentQuestionText

        nextBtn.setOnClickListener {
            gameModel.nextQuestion()
            questionText.text = gameModel.currentQuestionText
        }

        prevBtn.setOnClickListener {
            gameModel.prevQuestion()
            questionText.text = gameModel.currentQuestionText
        }
    }
}