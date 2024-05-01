package com.app.database.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.app.database.entity.GameSession
import com.app.database.entity.GameSessionQuestion
import com.app.database.entity.Question
import com.app.database.entity.Topic

data class GameSessionQuestionWithQuestion(
    val id: Int,
    val isAnswered: Boolean,
    val isCorrect: Boolean,
    @Embedded val question: Question
)


@Dao
interface GameSessionDao {

    @Query("SELECT * FROM game_session gs WHERE gs._id == 0")
    fun getGameSession(): GameSession

    @Query("SELECT * FROM topic t INNER JOIN (SELECT gsq._id AS id," +
            " gsq.is_answered AS isAnswered," +
            " gsq.is_correct AS isCorrect, " +
            "q.* FROM game_session_question gsq " +
            "INNER JOIN question q ON (gsq.question_id == q._id)) gqwq ON (t._id == gqwq.topic_id)")
    fun getGameSessionQuestions(): Map<Topic, List<GameSessionQuestionWithQuestion>>


    @Update
    fun updateGameSession(gameSession: GameSession)

}
