package com.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ViewFlipper
import com.app.R

class ScoreScreen : AppCompatActivity() {

    private  lateinit var viewFlipper: ViewFlipper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_screen)

        viewFlipper = findViewById(R.id.ViewFlipper)
        viewFlipper.flipInterval=2000
        viewFlipper.isAutoStart = true
        viewFlipper.startFlipping()

    }
}