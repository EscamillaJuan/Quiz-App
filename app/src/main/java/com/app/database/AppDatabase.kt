package com.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.database.dao.QuestionDao
import com.app.database.dao.TopicDao
import com.app.database.entity.Option
import com.app.database.entity.Question
import com.app.database.entity.Topic
import com.app.utils.InsertionQueries

@Database(entities = [Topic::class, Question::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun questionDao(): QuestionDao

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
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}