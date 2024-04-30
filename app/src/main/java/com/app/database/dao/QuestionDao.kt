package com.app.database.dao

import androidx.room.Dao


@Dao
interface QuestionDao {
//    @Query("SELECT * FROM question q INNER JOIN option o on (q._id == o.question_id)")
//    fun getQuestionWithOptions(): List<QuestionWithOptions>
}