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
    private val options = mutableListOf<Button>()
    private var mode = "medium"

    private fun setOptions(mode: String) {
        val options = mutableListOf<TextView>()
        options.add(findViewById(R.id.option_1))
        options.add(findViewById(R.id.option_2))
        options.add(findViewById(R.id.option_3))
        options.add(findViewById(R.id.option_4))
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
        options.add(findViewById(R.id.option_1))
        options.add(findViewById(R.id.option_2))
        options.add(findViewById(R.id.option_3))
        options.add(findViewById(R.id.option_4))


        questionText.text = gameModel.currentQuestionText
        questionsCounter.text = gameModel.counterText
        topicText.text = gameModel.topicText
        topicIcon.setImageResource(gameModel.topicIcon)
        mode = intent.getStringExtra(SELECTED_DIFFICULT).toString()
        gameModel.getOptions(mode, options)


        nextBtn.setOnClickListener {
            gameModel.nextQuestion(mode, options)
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
        }

        prevBtn.setOnClickListener {
            gameModel.prevQuestion(mode, options)
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
        }

        for( i in 0 until options.size - 1) {
            options[i].setOnClickListener {
                gameModel.checkAnswer(options, i, options[i].text)
                gameModel.extraHint(this)
                hintBtn.text = gameModel.currentHintText
                gameService.setUserAnswer(
                    gameModel.currentQuestionIsAnswered,
                    gameModel.currentQuestionIsCorrect,
                    options,
                    gameModel.currentQuestionAnswer,
                    gameModel.currentQuestionOptions,
                    textAnsweredQuestion
                )
            }
        }

    }
}