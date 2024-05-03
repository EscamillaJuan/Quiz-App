package com.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")

data class Score(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") val id: Int,
    val score: Int = 0,
    val user: String,
    val hints: Int
    )
