package com.app

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.app.database.AppDatabase
import com.app.view.ScoreItems
import com.app.view.scoreAdapter



class Activity5 : AppCompatActivity() {


    private val db = AppDatabase.get(this)
    private val scoreDao = db.scoreDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_5)
        val scoreItemDb = scoreDao.getScore()
        val scoreListView = findViewById<ListView>(R.id.listViewScore)
        val scoreItem = ScoreItems(scoreItemDb[0].id.toString(),scoreItemDb[0].user,scoreItemDb[0].score.toString())

/*      val scoreItem2 = ScoreItems(scoreItemDb[1].id.toString(),scoreItemDb[1].user,scoreItemDb[1].score.toString())
        val scoreItem3 = ScoreItems(scoreItemDb[2].id.toString(),scoreItemDb[2].user,scoreItemDb[2].score.toString())
        val scoreItem4 = ScoreItems(scoreItemDb[3].id.toString(),scoreItemDb[3].user,scoreItemDb[3].score.toString())
        val scoreItem5 = ScoreItems(scoreItemDb[4].id.toString(),scoreItemDb[4].user,scoreItemDb[4].score.toString())
*/
//        val scoreList = listOf(scoreItem, scoreItem2,scoreItem3,scoreItem4,scoreItem5)

        val scoreList = listOf(scoreItem)
        val adapter = scoreAdapter(this, scoreList)

        scoreListView.adapter = adapter


    }
}