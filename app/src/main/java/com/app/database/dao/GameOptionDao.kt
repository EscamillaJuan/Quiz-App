package com.app.database.dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.database.entity.GameOption

@Entity

@Dao
interface GameOptionDao {
    @Query("SELECT * FROM game_option")
    fun getGameOption(): GameOption

    @Update
    fun updateGameOption(gameOption: GameOption)


}