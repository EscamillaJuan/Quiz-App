package com.app.view

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.app.R
import com.app.database.AppDatabase


class ScoreRecords : AppCompatActivity() {


    private val db = AppDatabase.get(this)
    private val scoreDao = db.scoreDao()
    private val scoreItemsList = mutableListOf<ScoreItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.score_records)
        val scoreListView = findViewById<ListView>(R.id.listViewScore)
        val scoreItemDb = scoreDao.getScore()
        val size = if (scoreItemDb.size <= 20) scoreItemDb.size else 20
        for (j in 0..< size){
            val item = ScoreItems(
                pos = (j + 1).toString(),
                user = scoreItemDb[j].user,
                score = scoreItemDb[j].score.toString(),
                hints = scoreItemDb[j].hints.toString()
            )
            scoreItemsList.add(item)
        }
        val adapter = scoreAdapter(this, scoreItemsList)
        scoreListView.adapter = adapter



        /*
        val scoreItem = ScoreItems(pos.toString(),scoreItemDb[0].user,scoreItemDb[0].score.toString())
        val scoreList = listOf(scoreItem)
        val adapter = scoreAdapter(this, scoreList)
        scoreListView.adapter = adapter


         */





        /*val scoreItem2 = ScoreItems(scoreItemDb[1].id.toString(),scoreItemDb[1].user,scoreItemDb[1].score.toString())
        val scoreItem3 = ScoreItems(scoreItemDb[2].id.toString(),scoreItemDb[2].user,scoreItemDb[2].score.toString())
        val scoreItem4 = ScoreItems(scoreItemDb[3].id.toString(),scoreItemDb[3].user,scoreItemDb[3].score.toString())
        val scoreItem5 = ScoreItems(scoreItemDb[4].id.toString(),scoreItemDb[4].user,scoreItemDb[4].score.toString())
*/
   //     val scoreList = listOf(scoreItem, scoreItem2,scoreItem3,scoreItem4,scoreItem5)





    }
}