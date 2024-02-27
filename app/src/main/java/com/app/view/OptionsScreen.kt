package com.app.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.MainActivity
import com.app.R
import com.app.model.GameModel

class OptionsScreen : AppCompatActivity()  {

        private lateinit var opText: TextView
        private lateinit var prevBtn: Button
        private lateinit var workText: TextView
        private lateinit var bldIcon: ImageView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.options_screen)

            opText = findViewById(R.id.optionsText)
            prevBtn = findViewById(R.id.return_options_btn)
            workText = findViewById(R.id.workinprogressText)
            bldIcon = findViewById(R.id.workinprogress_icon)


            prevBtn.setOnClickListener {
                val intentprevBtn = Intent(this, MainActivity::class.java)
                startActivity(intentprevBtn)
            }


        }

}