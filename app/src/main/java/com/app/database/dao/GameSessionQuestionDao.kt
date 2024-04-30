package com.app.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.app.database.entity.GameSessionQuestion

@Dao
interface GameSessionQuestionDao {
    @Update
    fun updateQuestion(gameQuestion: GameSessionQuestion)
}