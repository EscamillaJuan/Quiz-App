package com.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    @PrimaryKey @ColumnInfo(name = "_id") val id: Int,
    @ColumnInfo(name = "topic_id") val topicId: Int,
    val text: String,
    val answer: String,
    @ColumnInfo(name = "option_1") val option1: String,
    @ColumnInfo(name = "option_2") val option2: String,
    @ColumnInfo(name = "option_3") val option3: String,
)