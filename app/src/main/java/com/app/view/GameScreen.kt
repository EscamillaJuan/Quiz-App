package com.app.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.R
import com.app.model.GameModel
const val SELECTED_DIFFICULT = "SELECTED_DIFFICULT"

class GameScreen : AppCompatActivity() {

    private val gameModel: GameModel by viewModels()
    private lateinit var nextBtn: ImageButton
    private lateinit var prevBtn: ImageButton
    private lateinit var hintBtn: Button
    private lateinit var questionText: TextView
    private lateinit var questionsCounter: TextView
    private lateinit var topicText: TextView
    private lateinit var topicIcon: ImageView
    private var mode = "medium"

    private fun setOptions(mode: String) {
        val answers = gameModel.getOptions(mode)
        val options = mutableListOf<TextView>()
        options.add(findViewById(R.id.option_1))
        options.add(findViewById(R.id.option_2))
        options.add(findViewById(R.id.option_3))
        options.add(findViewById(R.id.option_4))
        when (mode) {
            "easy" -> {
                for (i in 0 until 2) {
                    options[i].visibility = View.VISIBLE
                    options[i].visibility = View.VISIBLE
                    options[i].text = answers[i].keys.first()
                }
                for (i in 2 until 4) {
                    options[i].visibility = View.GONE
                    options[i].visibility = View.GONE
                }
            }

            "medium" -> {
                for (i in 0 until 3) {
                    options[i].visibility = View.VISIBLE
                    options[i].visibility = View.VISIBLE
                    options[i].text = answers[i].keys.first()
                }
                options[3].visibility = View.GONE
                options[3].visibility = View.GONE
            }

            else -> {
                for (i in 0 until 4) {
                    options[i].visibility = View.VISIBLE
                    options[i].visibility = View.VISIBLE
                    options[i].text = answers[i].keys.first()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        nextBtn = findViewById(R.id.next_btn)
        prevBtn = findViewById(R.id.prev_btn)
        hintBtn = findViewById(R.id.hint_btn)
        questionText = findViewById(R.id.question)
        questionsCounter = findViewById(R.id.question_number)
        topicText = findViewById(R.id.topic)
        topicIcon = findViewById(R.id.topic_icon)


        questionText.text = gameModel.currentQuestionText
        questionsCounter.text = gameModel.counterText
        topicText.text = gameModel.topicText
        topicIcon.setImageResource(gameModel.topicIcon)
        mode = intent.getStringExtra(SELECTED_DIFFICULT).toString()
        setOptions(mode)

        nextBtn.setOnClickListener {
            gameModel.nextQuestion()
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
            setOptions(mode)
        }

        prevBtn.setOnClickListener {
            gameModel.prevQuestion()
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
            setOptions(mode)
        }

    }
}