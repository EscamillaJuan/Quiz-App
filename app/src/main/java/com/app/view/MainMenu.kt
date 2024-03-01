package com.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.app.R


class MainMenu : AppCompatActivity() {
    private lateinit var openBtn: Button
    private lateinit var modeSp: Spinner
    private var mode = "medium"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        openBtn = findViewById(R.id.btn_jugar)
        modeSp = findViewById(R.id.spin_dificultad)

        ArrayAdapter.createFromResource(
            this,
            R.array.modes_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modeSp.adapter = it
        }
        openBtn.setOnClickListener {
            when (modeSp.selectedItem.toString()) {
                "Fácil" -> mode = "easy"
                "Medio" -> mode = "medium"
                "Dificil" -> mode = "hard"
            }
            val intent = Intent(this, GameScreen::class.java)
            intent.putExtra(SELECTED_DIFFICULT, mode)
            startActivity(intent)
        }
    }
}
