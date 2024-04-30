package com.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game_session_question")
data class GameSessionQuestion(
    @PrimaryKey @ColumnInfo(name = "_id") val id: Int,
    @ColumnInfo(name = "question_id") val questionId: Int,
    @ColumnInfo(name= "game_session_id") val gameSessionId: Int,
    @ColumnInfo(name = "is_answered") val isAnswered: Boolean,
    @ColumnInfo(name = "is_correct") val isCorrect: Boolean,
)
