package com.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_option")
data class GameOption(
    @PrimaryKey @ColumnInfo(name = "_id") val id: Int,
    val mode: Int,
    @ColumnInfo(name = "question_qty") val questionQty: Int,
    val hint: Boolean,
    val cine: Boolean,
    val arte: Boolean,
    val historia: Boolean,
    val musica: Boolean,
    val ciencia: Boolean,
    val tecnologia:Boolean
)