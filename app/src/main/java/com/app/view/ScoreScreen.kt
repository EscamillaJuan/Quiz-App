package com.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.recyclerview.widget.RecyclerView
import com.app.R
import com.app.database.AppDatabase

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


    private var score = 0
    private var correctAnswers = 0
    private var usedHints = 0
    private var unusedHints = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_screen)
        viewFlipper = findViewById(R.id.ViewFlipper)
        totalCorrectAnswers = findViewById(R.id.total_correct_answers)
        totalUsedHints = findViewById(R.id.total_used_hints)
        totalUnusedHints = findViewById(R.id.total_unused_hints)
        totalScore = findViewById(R.id.total_score)
        val scoreListView = findViewById<ListView>(R.id.listViewScore)

        viewFlipper.flipInterval = 2000
        viewFlipper.isAutoStart = true
        viewFlipper.startFlipping()

        val scoreItem = ScoreItems("1","AAA","123")
        val scoreItem2 = ScoreItems("2","BBB","333")
        val scoreItem3 = ScoreItems("3","BBB","333")
        val scoreItem4 = ScoreItems("4","BBB","333")
        val scoreItem5 = ScoreItems("5","BBB","333")

        val scoreList = listOf(scoreItem, scoreItem2,scoreItem3,scoreItem4,scoreItem5)
        val adapter = scoreAdapter(this, scoreList)

        scoreListView.adapter = adapter


        score = intent.getIntExtra(SCORE, 0)
        correctAnswers = intent.getIntExtra(TOTAL_ANSWERS, 0)
        usedHints = intent.getIntExtra(TOTAL_USED_HINTS, 0)
        unusedHints = intent.getIntExtra(TOTAL_UNUSED_HINTS, 0)
        totalScore.text = getString(R.string.final_score_text, score.toString())
        totalCorrectAnswers.text = getString(R.string.total_answers_text, correctAnswers.toString())
        totalUsedHints.text = getString(R.string.used_hints_text, usedHints.toString())
        totalUnusedHints.text = getString(R.string.unused_hints_text, unusedHints.toString())
    }
}