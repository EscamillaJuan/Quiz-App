package com.app.usecases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.app.R
import com.app.database.AppDatabase
import com.app.view.ScoreItems
import com.app.view.ScoreScreen
import com.app.view.scoreAdapter

class ScoreScreenFragment : Fragment(R.layout.fragment_score_screen) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_score_screen, container, false)
        val scoreListView = view.findViewById<ListView>(R.id.listViewScore)

        val db = AppDatabase.get(view.context)
        val scoreDao = db.scoreDao()
        val scoreItem = mutableListOf<ScoreItems>()
        val scoreItemDb = scoreDao.getScore()
        val size = if (scoreItemDb.size <= 5) scoreItem.size else 5
        for(i in 0..< size) {
            val item = ScoreItems(
                pos = (i + 1).toString(),
                user = scoreItemDb[i].user,
                score = scoreItemDb[i].score.toString(),
                hints = scoreItemDb[i].hints.toString()
            )
            scoreItem.add(item)
        }
        val adapter = scoreAdapter(view.context, scoreItem)
        scoreListView.adapter = adapter

        return view
    }
}