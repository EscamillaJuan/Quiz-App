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

    val topicThemes = listOf(
        "Cine",
        "Arte",
        "Historia",
        "Música",
        "Ciencia",
        "Tecnología"
    )

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
            val updatedGameOption = gameOption.copy(questionQty = value.toInt())
            gameOptionDao.updateGameOption(updatedGameOption)
        }

        hintswt.setOnCheckedChangeListener { _, isChecked ->
            val updatedGameOption = gameOption.copy(hint = isChecked)
            gameOptionDao.updateGameOption(updatedGameOption)
            hintstxt.text = if (isChecked) "Usaras pistas" else "No usaras pistas"
        }

        checkBox[0].setOnCheckedChangeListener { _, isChecked ->
            checkBox[0].isChecked = isChecked
            val updatedGameOption = gameOption.copy(cine = isChecked)
            gameOptionDao.updateGameOption(updatedGameOption)
        }
        checkBox[1].setOnCheckedChangeListener { _, isChecked ->
            checkBox[1].isChecked = isChecked
            val updatedGameOption = gameOption.copy(arte = isChecked)
            gameOptionDao.updateGameOption(updatedGameOption)
        }
        checkBox[2].setOnCheckedChangeListener { _, isChecked ->
            checkBox[2].isChecked = isChecked
            val updatedGameOption = gameOption.copy(historia = isChecked)
            gameOptionDao.updateGameOption(updatedGameOption)
        }
        checkBox[3].setOnCheckedChangeListener { _, isChecked ->
            checkBox[3].isChecked = isChecked
            val updatedGameOption = gameOption.copy(musica = isChecked)
            gameOptionDao.updateGameOption(updatedGameOption)
        }
        checkBox[4].setOnCheckedChangeListener { _, isChecked ->
            checkBox[4].isChecked = isChecked
            val updatedGameOption = gameOption.copy(ciencia = isChecked)
            gameOptionDao.updateGameOption(updatedGameOption)
        }
        checkBox[5].setOnCheckedChangeListener { _, isChecked ->
            checkBox[5].isChecked = isChecked
            val updatedGameOption = gameOption.copy(tecnologia = isChecked)
            gameOptionDao.updateGameOption(updatedGameOption)
        }

        modeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                val updatedGameOption = gameOption.copy(mode = position)
                gameOptionDao.updateGameOption(updatedGameOption)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}

