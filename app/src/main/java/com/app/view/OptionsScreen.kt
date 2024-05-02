package com.app.view


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.R
import com.app.database.AppDatabase
import com.google.android.material.slider.Slider



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
    private var selectedthemes: Int = 0


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
            if (checkBox[i].isChecked){selectedthemes++}
        }

        hintswt.isChecked = gameOption.hint
        hintstxt.text = if (hintswt.isChecked) "Usaras pistas" else "No usaras pistas"
        //Para inicializar el numero de preguntas
        questionTxt.text = "Habrá ${gameOption.questionQty} preguntas en juego"
        questionSlider.value = gameOption.questionQty.toFloat()
        modeSp.setSelection(gameOption.mode)


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
            if (isChecked) {
                selectedthemes++
            } else {
                selectedthemes--
            }
        }
        checkBox[1].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(arte = isChecked)
            )
            if (isChecked) {
                selectedthemes++
            } else {
                selectedthemes--
            }
        }
        checkBox[2].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(historia = isChecked)
            )
            if (isChecked) {
                selectedthemes++
            } else {
                selectedthemes--
            }
        }
        checkBox[3].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(musica = isChecked)
            )
            if (isChecked) {
                selectedthemes++
            } else {
                selectedthemes--
            }
        }
        checkBox[4].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(ciencia = isChecked)
            )
            if (isChecked) {
                selectedthemes++
            } else {
                selectedthemes--
            }
        }
        checkBox[5].setOnCheckedChangeListener { _, isChecked ->
            val option = gameOptionDao.getGameOption()
            gameOptionDao.updateGameOption(
                option.copy(tecnologia = isChecked)
            )
            if (isChecked) {
                selectedthemes++
            } else {
                selectedthemes--
            }
        }

        questionSlider.addOnChangeListener { _, value, _ ->

            val option = gameOptionDao.getGameOption()
            if (value.toInt() <= selectedthemes * 5) {
                questionTxt.text = "Habrá ${value.toInt()} preguntas en juego"
                gameOptionDao.updateGameOption(
                    option.copy(questionQty = value.toInt())
                )
            } else {
                questionSlider.value = 5.toFloat()
                gameOptionDao.updateGameOption(
                    option.copy(questionQty = questionSlider.value.toInt())
                )
                Toast.makeText(
                    this,
                    "No puede haber mas de 5 preguntas",
                    Toast.LENGTH_SHORT
                ).show()
            }
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

