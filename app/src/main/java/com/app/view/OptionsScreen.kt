package com.app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import com.app.MainActivity
import com.app.R
import com.app.database.AppDatabase
import com.app.database.dao.GameSessionDao
import com.app.database.entity.GameOption
import com.app.database.entity.GameSession
import com.app.model.GameModel
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.Objects

class OptionsScreen : AppCompatActivity() {
    private val db: AppDatabase = AppDatabase.get(this)
    private val gameOptionDao = db.gameOptionDao()
    private lateinit var opText: TextView
    private lateinit var themeText: TextView
    private lateinit var questionTxt: TextView
    private lateinit var questionSlider: Slider
    private lateinit var modeSp: Spinner
    private lateinit var dificultadtxt: TextView
    private lateinit var hintstxt: TextView
    private lateinit var hintswt: Switch
    private val checkBox = mutableListOf<CheckBox>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.options_screen)

        //Inicializacion de los elementos del layout
        modeSp = findViewById(R.id.difficulty_spin)
        opText = findViewById(R.id.optionsText)
        themeText = findViewById(R.id.TemasText)
        questionTxt = findViewById(R.id.Questiontext)
        checkBox.add(findViewById(R.id.Theme1CheckBox))
        checkBox.add(findViewById(R.id.Theme2CheckBox))
        checkBox.add(findViewById(R.id.Theme3CheckBox))
        checkBox.add(findViewById(R.id.Theme4CheckBox))
        checkBox.add(findViewById(R.id.Theme5CheckBox))
        checkBox.add(findViewById(R.id.Theme6CheckBox))

        questionSlider = findViewById(R.id.QuestionsSlider)
        dificultadtxt = findViewById(R.id.DificultadText)
        hintswt = findViewById(R.id.hint_stw)
        hintstxt = findViewById(R.id.hints_txt)

        ArrayAdapter.createFromResource(
            this,
            R.array.modes_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modeSp.adapter = it
        }

        val gameOption = gameOptionDao.getGameOption()
        val topics = listOf(
            gameOption.cine,
            gameOption.arte,
            gameOption.historia,
            gameOption.musica,
            gameOption.ciencia,
            gameOption.tecnologia
        )
        for (i in checkBox.indices) {
            checkBox[i].isChecked = topics[i]
        }

        hintswt.isChecked = gameOption.hint
        questionSlider.value = gameOption.questionQty.toFloat()
        modeSp.setSelection(gameOption.mode)

        questionSlider.addOnChangeListener { _, value, _ ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(questionQty = value.toInt())
            )
        }

        hintswt.setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(hint = isChecked)
            )
            hintstxt.text = if (isChecked) "Usaras pistas" else "No usaras pistas"
        }

        checkBox[0].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(cine = isChecked)
            )
        }
        checkBox[1].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(arte = isChecked)
            )
        }
        checkBox[2].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(historia = isChecked)
            )
        }
        checkBox[3].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(musica = isChecked)
            )
        }
        checkBox[4].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(ciencia = isChecked)
            )
        }
        checkBox[5].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(tecnologia = isChecked)
            )
        }

        modeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val option = gameOptionDao.getGameOption()
                gameOptionDao.updateGameOption(
                    option.copy(mode = position)
                )
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}

