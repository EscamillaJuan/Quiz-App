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
import com.app.view.SELECTED_DIFFICULT

class NewGame : Fragment() {
    private lateinit var continueGameBtn: Button
    private lateinit var newGameBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_game, container, false)
        val mode = arguments?.getString(SELECTED_DIFFICULT)
        newGameBtn = view.findViewById(R.id.new_game_btn)

        newGameBtn.setOnClickListener {
            val intent = Intent(requireContext(), GameScreen::class.java)
            intent.putExtra(SELECTED_DIFFICULT, mode)
            startActivity(intent)
            activity?.supportFragmentManager?.popBackStack()
        }
        return view
    }
}