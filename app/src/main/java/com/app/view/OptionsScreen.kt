package com.app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
    private lateinit var checkbx1: CheckBox
    private lateinit var checkbx2: CheckBox
    private lateinit var checkbx3: CheckBox
    private lateinit var checkbx4: CheckBox
    private lateinit var checkbx5: CheckBox
    private lateinit var checkbx6: CheckBox
    private lateinit var prevBtn: Button
    private lateinit var modeSp: Spinner
    private lateinit var dificultadtxt: TextView
    private lateinit var hintstxt: TextView
    private lateinit var hintstw: Switch
    private lateinit var selectedthemes: MutableList<String>
    private lateinit var setOptions: GameOption


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.options_screen)

        //Inicializacion de los elementos del layout
        prevBtn = findViewById(R.id.return_options_btn)
        modeSp = findViewById(R.id.difficulty_spin)
        opText = findViewById(R.id.optionsText)
        themeText = findViewById(R.id.TemasText)
        questionTxt = findViewById(R.id.Questiontext)
        checkbx1 = findViewById(R.id.Theme1CheckBox)
        checkbx2 = findViewById(R.id.Theme2CheckBox)
        checkbx3 = findViewById(R.id.Theme3CheckBox)
        checkbx4 = findViewById(R.id.Theme4CheckBox)
        checkbx5 = findViewById(R.id.Theme5CheckBox)
        checkbx6 = findViewById(R.id.Theme6CheckBox)
        questionSlider = findViewById(R.id.QuestionsSlider)
        dificultadtxt = findViewById(R.id.DificultadText)
        hintstw = findViewById(R.id.hint_stw)
        hintstxt = findViewById(R.id.hints_txt)

        //Creacion del adaptador para el spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.modes_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modeSp.adapter = it
        }

        //Inicializacion de los elementos del layout con los valores de la base de datos
        val gameOption = gameOptionDao.getGameOption()
        checkbx1.isChecked = gameOption.cine
        checkbx2.isChecked = gameOption.arte
        checkbx3.isChecked = gameOption.historia
        checkbx4.isChecked = gameOption.musica
        checkbx5.isChecked = gameOption.ciencia
        checkbx6.isChecked = gameOption.tecnologia
        hintstw.isChecked = gameOption.hint
        questionSlider.value = gameOption.questionQty.toFloat()
        modeSp.setSelection(gameOption.mode)

        //Creo el slider
        questionSlider.addOnChangeListener { slider, value, fromUser ->
        }

        //Agrego listener de los checkboxes


        /*if (checkbx1.isChecked){ selectedthemes.add(checkbx1.text.toString())}
        if (checkbx2.isChecked){ selectedthemes.add(checkbx2.text.toString())}
        if (checkbx3.isChecked){ selectedthemes.add(checkbx3.text.toString())}
        if (checkbx4.isChecked){ selectedthemes.add(checkbx4.text.toString())}
        if (checkbx5.isChecked){ selectedthemes.add(checkbx5.text.toString())}
        if (checkbx6.isChecked){ selectedthemes.add(checkbx6.text.toString())}*/

        //Funcionamiento del switch
        hintstw.setOnCheckedChangeListener { buttonView, isChecked ->
        }

        //Agrego los cambios a la base de datos al cerrar el activity
        prevBtn.setOnClickListener {
            gameOptionDao.updateGameOption(
                GameOption(
                    0,
                    modeSp.selectedItemId.toInt(),
                    questionSlider.value.toInt(),
                    hintstw.isChecked,
                    checkbx1.isChecked,
                    checkbx2.isChecked,
                    checkbx3.isChecked,
                    checkbx4.isChecked,
                    checkbx5.isChecked,
                    checkbx6.isChecked,
                )
            )
            finish()
        }

    }
}

