package com.app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.app.database.AppDatabase
import com.app.usecases.NewGame
import com.app.view.GameScreen
import com.app.view.OptionsScreen
import com.app.view.ScoreScreen

val btnColor = Color.parseColor("#624D1B")
val btnWrong = Color.parseColor("#CC0000")
val btnRight = Color.parseColor("#99CC00")

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var openBtn: Button
    private lateinit var optionBtn: Button

    private lateinit var scoreBtn: Button

    var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.get(this)
        setContentView(R.layout.main_activity)
        openBtn = findViewById(R.id.play_btn)
        optionBtn = findViewById(R.id.option_btn)
        scoreBtn = findViewById(R.id.score_btn)

        onBackPressedDispatcher.addCallback(
            this@MainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        return
                    } else {
                        if (doubleBackToExitPressedOnce) {
                            finish()
                        } else {
                            doubleBackToExitPressedOnce = true
                            Toast.makeText(
                                this@MainActivity,
                                "Presiona de nuevo para salir",
                                Toast.LENGTH_SHORT
                            ).show()

                            Handler(Looper.getMainLooper()).postDelayed({
                                doubleBackToExitPressedOnce = false
                            }, 2000)
                        }
                    }
                }
            }
        )

        openBtn.setOnClickListener {
            val gameSession = db.gameSessionDao().getGameSession()
            if (gameSession.done) {
                val intent = Intent(this, GameScreen::class.java)
                startActivity(intent)
            } else {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val newGameFragment = NewGame()
                fragmentTransaction.add(R.id.root_layout, newGameFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }

        optionBtn.setOnClickListener {
            val intentionBtn = Intent(this, OptionsScreen::class.java)
            startActivity(intentionBtn)
        }
        scoreBtn.setOnClickListener {
            val intentionBtn = Intent(this, ScoreScreen::class.java)
            startActivity(intentionBtn)
        }
    }
}
