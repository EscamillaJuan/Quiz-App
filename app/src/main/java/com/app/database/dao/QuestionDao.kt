package com.app.database.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import com.app.database.entity.Option
import com.app.database.entity.Question
import com.app.database.entity.Topic



@Dao
interface QuestionDao {
//    @Query("SELECT * FROM question q INNER JOIN option o on (q._id == o.question_id)")
//    fun getQuestionWithOptions(): List<QuestionWithOptions>
}