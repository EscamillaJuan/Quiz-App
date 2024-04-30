package com.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.database.dao.GameSessionDao
import com.app.database.dao.GameSessionQuestionDao
import com.app.database.dao.QuestionDao
import com.app.database.dao.TopicDao
import com.app.database.entity.GameSession
import com.app.database.entity.GameSessionQuestion
import com.app.database.entity.Question
import com.app.database.entity.Topic
import com.app.utils.InsertionQueries

@Database(entities = [Topic::class, Question::class, GameSession::class, GameSessionQuestion::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun questionDao(): QuestionDao
    abstract fun gameSessionDao(): GameSessionDao
    abstract fun gameSessionQuestionsDao(): GameSessionQuestionDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase ? = null
        private val queries = InsertionQueries()

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "quiz-db"
                ).allowMainThreadQueries().addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        for (query in queries.topicInsertion) {
                            db.execSQL(query)
                        }
                        for (query in queries.questionInsertion) {
                            db.execSQL(query)
                        }
                        db.execSQL(queries.gameSessionInsert)
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}