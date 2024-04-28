package com.app.database.dao

import androidx.room.Dao
import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Query
import androidx.room.Relation
import com.app.database.entity.Option
import com.app.database.entity.Question
import com.app.database.entity.Topic



@Dao
interface TopicDao {
    @Query("SELECT * FROM topic")
    fun getTopics(): List<Topic>

    @Query("SELECT * FROM topic t INNER JOIN question q on (t._id == q.topic_id) ORDER BY RANDOM() LIMIT :count")
    fun getTopicWithQuestions(count: Int): Map<Topic, List<Question>>
}
