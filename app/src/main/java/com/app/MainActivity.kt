package com.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.view.GameScreen

class MainActivity : AppCompatActivity() {
    private lateinit var openBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        openBtn = findViewById(R.id.play_btn)

        openBtn.setOnClickListener {
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
    }
}