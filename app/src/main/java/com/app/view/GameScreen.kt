package com.app.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.R
import com.app.database.AppDatabase

import com.app.database.dao.ScoreDao
import com.app.database.entity.GameOption
import com.app.database.entity.Score

import com.app.model.GameModel
import com.app.service.IGameService
import com.app.service.implementation.GameServiceImpl
import com.app.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val NEW_GAME = "NEW_GAME"

class GameScreen : AppCompatActivity() {
    private lateinit var gameModel: GameModel
    private val db = AppDatabase.get(this)
    private val gameSessionDao = db.gameSessionDao()
    private val gameOptionDao = db.gameOptionDao()

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


    private var newGame = true
    var doubleBackToExitPressedOnce = false
    private val modes = listOf(
        "easy",
        "medium",
        "hard"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        newGame = intent.getBooleanExtra(NEW_GAME, true)
        val gameOption = gameOptionDao.getGameOption()
        val gameSession = gameSessionDao.getGameSession()
        val mode = modes[gameOption.mode]
        var questionQuantity = gameOption.questionQty


        onBackPressedDispatcher.addCallback(this@GameScreen, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    gameSessionDao.updateGameSession(
                        gameSession.copy(
                            questionQty = questionQuantity,
                            finished = false,
                            hintEnable = gameOption.hint,
                            hintQty = gameModel.unusedHintsCounter,
                            score = gameModel.totalScore,
                            answeredQuestionsQty = gameModel.answeredQuestionCounter
                        )
                    )
                    finish()
                }
                this@GameScreen.doubleBackToExitPressedOnce = true
                supportFragmentManager.popBackStack()
                Toast.makeText(
                    this@GameScreen,
                    "Presiona de nuevo para salir",
                    Toast.LENGTH_SHORT
                ).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    doubleBackToExitPressedOnce = false
                }, 2000)
            }
        })

        gameModel = ViewModelProvider(this, ViewModelFactory(db, newGame))[GameModel::class.java]

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

        if (!gameOption.hint) {
            hintBtn.visibility = View.GONE
        }

        if(!newGame) {
            gameModel.hint = gameSession.hintQty
            gameModel.score = gameSession.score
            questionQuantity = gameSession.questionQty
            gameModel.questionCounter = gameSession.answeredQuestionsQty
            if(!gameSession.hintEnable) {
                hintBtn.visibility = View.GONE
            }
        }

        hintBtn.text = gameModel.currentHintText
        questionText.text = gameModel.currentQuestionText
        questionsCounter.text = gameModel.counterText
        topicText.text = gameModel.topicText
        topicIcon.setImageResource(gameModel.topicIcon)



        gameModel.getAnswerOptions(mode)
        gameModel.getOptions(mode, options)
        gameService.setUserAnswer(
            gameModel.currentQuestionIsAnswered,
            gameModel.currentQuestionIsCorrect,
            options,
            gameModel.currentQuestionAnswer,
            gameModel.currentQuestionOptions,
            textAnsweredQuestion
        )
        gameModel.incorrectButtonIndexList(options, false, mode)


        nextBtn.setOnClickListener {
            gameModel.nextQuestion(mode, options)
            questionText.text = gameModel.currentQuestionText
            questionsCounter.text = gameModel.counterText
            topicText.text = gameModel.topicText
            topicIcon.setImageResource(gameModel.topicIcon)
            gameModel.incorrectButtonIndexList(options, false, mode)
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
            gameModel.incorrectButtonIndexList(options, false, mode)
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
            gameModel.incorrectButtonIndexList(options, true, mode)

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

                gameModel.scoreCounter(mode)
                if (gameModel.answeredQuestionCounter > questionQuantity - 1) {

                    val intent = Intent(this, ScoreScreen::class.java)
                    intent.putExtra(SCORE, gameModel.totalScore)
                    intent.putExtra(TOTAL_USED_HINTS, gameModel.usedHintsCounter)
                    intent.putExtra(TOTAL_UNUSED_HINTS, gameModel.unusedHintsCounter)
                    intent.putExtra(TOTAL_ANSWERS, gameModel.correctAnswersCounter)
                    CoroutineScope(Dispatchers.Main).launch {
                        gameSessionDao.updateGameSession(
                            gameSession.copy(
                                questionQty = questionQuantity,
                                finished = true,
                                hintEnable = gameOption.hint,
                                hintQty = gameModel.unusedHintsCounter,
                                score = gameModel.totalScore,
                                answeredQuestionsQty = gameModel.answeredQuestionCounter
                            )
                        )
                        delay(1000)
                        finish()
                        startActivity(intent)
                    }

                } else {
                    CoroutineScope(Dispatchers.Main).launch {
//                        delay(1000)
//                        nextBtn.performClick()
                    }
                }

            }
        }
    }
}