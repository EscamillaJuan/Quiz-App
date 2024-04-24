package com.app.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.MainActivity
import com.app.R
import com.app.model.GameModel
import com.google.android.material.slider.Slider

class OptionsScreen : AppCompatActivity()  {
        private lateinit var opText: TextView
        private lateinit var themeText: TextView
        private lateinit var questionTxt: TextView
        private lateinit var questionSlider: Slider
        private lateinit var checkbx1: CheckBox
        private lateinit var checkbx2: CheckBox
        private lateinit var checkbx3: CheckBox
        private lateinit var checkbx4: CheckBox
        private lateinit var checkbx5: CheckBox
        private lateinit var checkbx6: CheckBox
        private lateinit var prevBtn: Button
        private lateinit var modeSp: Spinner
        private lateinit var dificultadtxt: TextView

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.options_screen)
            prevBtn = findViewById(R.id.return_options_btn)
            modeSp = findViewById(R.id.difficulty_spin)
            opText = findViewById(R.id.optionsText)
            themeText = findViewById(R.id.TemasText)
            questionTxt = findViewById(R.id.Questiontext)
            checkbx1=findViewById(R.id.Theme1CheckBox)
            checkbx2=findViewById(R.id.Theme2CheckBox)
            checkbx3=findViewById(R.id.Theme3CheckBox)
            checkbx4=findViewById(R.id.Theme4CheckBox)
            checkbx5=findViewById(R.id.Theme5CheckBox)
            checkbx6=findViewById(R.id.Theme6CheckBox)
            questionSlider = findViewById(R.id.QuestionsSlider)
            dificultadtxt = findViewById(R.id.DificultadText)

            ArrayAdapter.createFromResource(
                this,
                R.array.modes_array,
                android.R.layout.simple_spinner_item
            ).also{it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modeSp.adapter = it}

            //Creo el slider



            prevBtn.setOnClickListener {
                finish()
            }

        }

}