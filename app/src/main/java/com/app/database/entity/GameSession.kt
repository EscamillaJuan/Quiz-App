package com.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_session")
data class GameSession(
    @PrimaryKey @ColumnInfo(name = "_id") val id: Int,
    val mode: String,
    @ColumnInfo(name = "question_qty") val questionQty: Int,
    @ColumnInfo(name = "hint_qty") val hintQty: Int,
    val done: Boolean
)
