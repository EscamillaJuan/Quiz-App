package com.app.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.MainActivity
import com.app.R
import com.app.model.GameModel

class OptionsScreen : AppCompatActivity()  {
        private lateinit var opText: TextView
        private lateinit var themeText: TextView
        private lateinit var checkbx: List<CheckBox>
        private lateinit var prevBtn: Button
        private lateinit var modeSp: Spinner

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.options_screen)
            modeSp = findViewById(R.id.difficulty_spin)
            opText = findViewById(R.id.optionsText)
            themeText = findViewById(R.id.TemasText)



            ArrayAdapter.createFromResource(
                this,
                R.array.modes_array,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                modeSp.adapter = it
            }




            prevBtn.setOnClickListener {
                finish()
            }

        }

}