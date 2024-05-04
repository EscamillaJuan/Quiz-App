package com.app.usecases

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.app.R
import com.app.view.GameScreen
import com.app.view.NEW_GAME

class NewGame : Fragment() {
    private lateinit var continueGameBtn: Button
    private lateinit var newGameBtn: Button

    private fun startGame(newGame: Boolean){
        val intent = Intent(requireContext(), GameScreen::class.java)
        intent.putExtra(NEW_GAME, newGame)
        startActivity(intent)
        activity?.supportFragmentManager?.popBackStack()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_game, container, false)
        newGameBtn = view.findViewById(R.id.new_game_btn)
        continueGameBtn = view.findViewById(R.id.continue_game_btn)

        newGameBtn.setOnClickListener {
            startGame(true)
        }
        continueGameBtn.setOnClickListener{
            startGame(false)
        }
        return view
    }
}