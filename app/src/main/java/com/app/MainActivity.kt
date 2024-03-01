package com.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.app.view.GameScreen
import com.app.view.OptionsScreen

class MainActivity : AppCompatActivity() {
    private lateinit var openBtn : Button
    private lateinit var optionBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        openBtn = findViewById(R.id.play_btn)
        optionBtn = findViewById(R.id.option_btn)

        openBtn.setOnClickListener {
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
        optionBtn.setOnClickListener {
            val intentionBtn = Intent(this, OptionsScreen::class.java)
            startActivity(intentionBtn)
        }
    }
}