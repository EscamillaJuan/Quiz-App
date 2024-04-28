package com.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "option")
data class Option(
    @PrimaryKey @ColumnInfo(name = "_id") val id: Int,
    val text: String,
    @ColumnInfo(name = "question_id") val questionId: Int
)
