package com.app.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.app.database.entity.GameOption
import com.app.database.entity.Question
import com.app.database.entity.Topic



@Dao
interface TopicDao {
    @Query("SELECT * FROM topic")
    fun getTopics(): List<Topic>

    @Query("SELECT * FROM topic t " +
            "INNER JOIN question q ON (t._id == q.topic_id) " +
            "WHERE t._id IN (:selectedTopicIds) " +
            "ORDER BY RANDOM() " +
            "LIMIT :count")
    fun getTopicWithQuestions(count: Int, selectedTopicIds: List<Int>): Map<Topic, List<Question>>

//    @Query()
//    fun getPreviousGame(gameOption: GameOption)
}
