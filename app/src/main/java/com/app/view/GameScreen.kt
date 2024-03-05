package com.app.view

import android.content.Intent
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        gameModel.incorrectButtonIndexList(options,false,mode)




        nextBtn.setOnClickListener {
            gameModel.nextQuestion(mode, options)
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
            gameModel.incorrectButtonIndexList(options,false,mode)
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
            gameModel.incorrectButtonIndexList(options,false,mode)
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
            if (gameModel.currentQuestionIsAnswered || gameModel.unusedHintsCounter == 0) return@setOnClickListener
            gameModel.currentHint(this)
            hintBtn.text = gameModel.currentHintText
            gameModel.checkHint(options, mode, this, textAnsweredQuestion)
            gameModel.incorrectButtonIndexList(options,true, mode)

        }

        for (i in options.indices) {
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
                gameModel.scoreCounter(mode);
                if (gameModel.answeredQuestionCounter == 10) {
                    val intent = Intent(this, ScoreScreen::class.java)
                    intent.putExtra(SCORE, gameModel.totalScore)
                    intent.putExtra(TOTAL_USED_HINTS, gameModel.usedHintsCounter)
                    intent.putExtra(TOTAL_UNUSED_HINTS, gameModel.unusedHintsCounter)
                    intent.putExtra(TOTAL_ANSWERS, gameModel.correctAnswersCounter)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000)
                        startActivity(intent)
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000)
                        nextBtn.performClick()
                    }
                }

            }
        }
    }
}