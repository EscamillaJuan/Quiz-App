package com.app.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.app.database.entity.Question
import com.app.database.entity.Topic

interface ScoreDao {

    @Dao
    interface TopicDao {
        @Query("SELECT * FROM score")
        fun getScore(): List<Topic>

    //   @Query("SELECT * FROM score t INNER JOIN question q on (t._id == q.topic_id) ORDER BY RANDOM() LIMIT :count")
    //  fun getScoreWith(count: Int): Map<Topic, List<Question>>

    }
}