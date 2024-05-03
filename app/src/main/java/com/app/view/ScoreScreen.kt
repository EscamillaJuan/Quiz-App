package com.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Query
import com.app.R
import com.app.database.AppDatabase
import com.app.database.entity.Score
import com.app.model.GameModel
import com.app.usecases.NewGame
import com.app.usecases.ScoreScreenFragment

const val SCORE = "SCORE"
const val TOTAL_ANSWERS = "TOTAL_ANSWERS"
const val TOTAL_USED_HINTS = "TOTAL_USED_HINTS"
const val TOTAL_UNUSED_HINTS = "TOTAL_UNUSED_HINTS"

class ScoreScreen : AppCompatActivity() {
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var totalCorrectAnswers: TextView
    private lateinit var totalUsedHints: TextView
    private lateinit var totalUnusedHints: TextView
    private lateinit var totalScore: TextView

    private val db = AppDatabase.get(this)
    private val scoreDao = db.scoreDao()
    private lateinit var gameModel: GameModel


    private var score = 0
    private var correctAnswers = 0
    private var usedHints = 0
    private var unusedHints = 0
    private var lastId = 0
    private val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_screen)
        viewFlipper = findViewById(R.id.ViewFlipper)
        totalCorrectAnswers = findViewById(R.id.total_correct_answers)
        totalUsedHints = findViewById(R.id.total_used_hints)
        totalUnusedHints = findViewById(R.id.total_unused_hints)
        totalScore = findViewById(R.id.total_score)

        viewFlipper.flipInterval = 2000
        viewFlipper.isAutoStart = true
        viewFlipper.startFlipping()


        score = intent.getIntExtra(SCORE, 0)
        correctAnswers = intent.getIntExtra(TOTAL_ANSWERS, 0)
        usedHints = intent.getIntExtra(TOTAL_USED_HINTS, 0)
        unusedHints = intent.getIntExtra(TOTAL_UNUSED_HINTS, 0)
        totalScore.text = getString(R.string.final_score_text, score.toString())
        totalCorrectAnswers.text = getString(R.string.total_answers_text, correctAnswers.toString())
        totalUsedHints.text = getString(R.string.used_hints_text, usedHints.toString())
        totalUnusedHints.text = getString(R.string.unused_hints_text, unusedHints.toString())

        scoreDao.insertScore(Score(lastId, score, "AAA" , usedHints))


        /*
        val scoreScreenFragment = ScoreScreenFragment()
        fragmentTransaction.add(R.id.root_layout,scoreScreenFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        */


    }
}