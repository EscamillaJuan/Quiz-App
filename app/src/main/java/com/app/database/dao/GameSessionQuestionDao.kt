package com.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.database.entity.GameSessionQuestion

@Dao
interface GameSessionQuestionDao {

    @Insert
    fun insertGameQuestion(gameSessionQuestion: GameSessionQuestion)

    @Query("DELETE FROM game_session_question")
    fun deleteAllQuestions()

    @Query("SELECT * FROM game_session_question WHERE _id = :id")
    fun getGameSessionQuestion(id: Int): GameSessionQuestion
    @Update
    fun updateGameQuestions(gameSessionQuestion: GameSessionQuestion)

}