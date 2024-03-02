package com.app.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.R
import com.app.btnColor
import com.app.model.GameModel

import com.app.service.IGameService
import com.app.service.implementation.GameServiceImpl

const val SELECTED_DIFFICULT = "SELECTED_DIFFICULT"

class GameScreen : AppCompatActivity() {

    private val gameModel: GameModel by viewModels()
    private val gameService: IGameService = GameServiceImpl()
    private lateinit var rootLayout: LinearLayout
    private lateinit var textAnsweredQuestion: TextView
    private lateinit var nextBtn: ImageButton
    private lateinit var prevBtn: ImageButton
    private lateinit var hintBtn: Button
    private lateinit var questionText: TextView
    private lateinit var questionsCounter: TextView
    private lateinit var topicText: TextView
    private lateinit var topicIcon: ImageView
    private val options = mutableListOf<Button>()
    private var mode = "medium"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        rootLayout = findViewById(R.id.root_layout)
        textAnsweredQuestion = findViewById(R.id.text_answered_question)
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

        hintBtn.text = gameModel.currentHintText
        questionText.text = gameModel.currentQuestionText
        questionsCounter.text = gameModel.counterText
        topicText.text = gameModel.topicText
        topicIcon.setImageResource(gameModel.topicIcon)
        mode = intent.getStringExtra(SELECTED_DIFFICULT).toString()
        gameModel.getOptions(mode, options)
        gameService.setUserAnswer(
            gameModel.currentQuestionIsAnswered,
            gameModel.currentQuestionIsCorrect,
            options,
            gameModel.currentQuestionAnswer,
            gameModel.currentQuestionOptions,
            textAnsweredQuestion
        )


        nextBtn.setOnClickListener {
            gameModel.nextQuestion(mode, options)
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
            for (i in 0 until options.size) options[i].setBackgroundColor(btnColor)
            gameService.setUserAnswer(
                gameModel.currentQuestionIsAnswered,
                gameModel.currentQuestionIsCorrect,
                options,
                gameModel.currentQuestionAnswer,
                gameModel.currentQuestionOptions,
                textAnsweredQuestion
            )
        }

        prevBtn.setOnClickListener {
            gameModel.prevQuestion(mode, options)
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
            gameService.setUserAnswer(
                gameModel.currentQuestionIsAnswered,
                gameModel.currentQuestionIsCorrect,
                options,
                gameModel.currentQuestionAnswer,
                gameModel.currentQuestionOptions,
                textAnsweredQuestion
            )
        }

        hintBtn.setOnClickListener { _ ->
            if (gameModel.currentQuestionIsAnswered) return@setOnClickListener
            gameModel.currentHint(this)
            hintBtn.text = gameModel.currentHintText
            gameModel.checkHint(options, mode, this, textAnsweredQuestion)
        }

        for (i in options.indices) {
            options[i].setOnClickListener {
                gameModel.checkAnswer(options, i, options[i].text)
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