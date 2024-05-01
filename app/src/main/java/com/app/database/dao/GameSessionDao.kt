package com.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.database.entity.GameSession
import com.app.database.entity.GameSessionQuestion
import com.app.database.entity.Topic

@Dao
interface GameSessionDao {

    @Query("SELECT * FROM game_session gs WHERE gs._id == 0")
    fun getGameSession(): GameSession

    @Query(
        "SELECT t.*, q.*, gsq.* FROM game_session gs " +
                "INNER JOIN game_session_question gsq ON gs._id = gsq.game_session_id " +
                "INNER JOIN question q ON gsq.question_id = q._id " +
                "INNER JOIN topic t ON q.topic_id = t._id " +
                "WHERE gs._id = 0"
    )
    fun getTopicsWithQuestionsFromSession(): Map<Topic, List<GameSessionQuestion>>

    @Update
    fun updateGameSession(gameSession: GameSession)

    @Query("UPDATE game_session SET done = :value WHERE _id = 0")
    fun saveGameSession(value: Boolean)
}