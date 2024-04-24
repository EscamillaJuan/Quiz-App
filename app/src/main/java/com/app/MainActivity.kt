package com.app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.app.view.GameScreen
import com.app.view.OptionsScreen
import com.app.view.SELECTED_DIFFICULT
import com.app.view.ScoreScreen

val btnColor = Color.parseColor("#624D1B")
val btnWrong = Color.parseColor("#CC0000")
val btnRight = Color.parseColor("#99CC00")

class MainActivity : AppCompatActivity() {
    private lateinit var openBtn: Button
    private lateinit var optionBtn: Button
    //private lateinit var modeSp: Spinner
    private lateinit var scoreBtn : Button
    private var mode = "medium"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        openBtn = findViewById(R.id.play_btn)
        //modeSp = findViewById(R.id.difficulty_spin)
        optionBtn = findViewById(R.id.option_btn)
        scoreBtn = findViewById(R.id.score_btn)
/*
    ArrayAdapter.createFromResource(
            this,
            R.array.modes_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modeSp.adapter = it
        }

 */
    
  
        openBtn.setOnClickListener {
            /*when (modeSp.selectedItem.toString()) {
                "Fácil" -> mode = "easy"
                "Medio" -> mode = "medium"
                "Dificil" -> mode = "hard"
            }*/

            val intent = Intent(this, GameScreen::class.java)
            intent.putExtra(SELECTED_DIFFICULT, mode)
            startActivity(intent)
        }

        optionBtn.setOnClickListener {
            val intentionBtn = Intent(this, OptionsScreen::class.java)
            startActivity(intentionBtn)
        }
        scoreBtn.setOnClickListener{
            val intentionBtn = Intent(this, ScoreScreen::class.java)
            startActivity(intentionBtn)
        }
    }
}
