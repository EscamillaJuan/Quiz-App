package com.app.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.database.entity.Score
@Dao
interface ScoreDao {
    @Query("SELECT * FROM score ORDER BY score DESC, hints ASC")
    fun getScore(): List<Score>

    @Insert
    fun insertScore(score: Score)

    @Query("SELECT MAX(_id) FROM score")
    fun getMaxId():Int

}





