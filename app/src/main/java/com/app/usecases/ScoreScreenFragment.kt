package com.app.usecases

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.app.R
import com.app.view.GameScreen
import com.app.view.NEW_GAME
import com.app.view.ScoreScreen

class ScoreScreenFragment : Fragment(R.layout.fragment_score_screen) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_score_screen, container, false)

        return view
    }
}