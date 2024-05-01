package com.app.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.database.AppDatabase
import com.app.model.GameModel

class ViewModelFactory(private val appDatabase: AppDatabase, private val numberOfQuestions: Int, private val selectedTopicsId: List<Int>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameModel(appDatabase, numberOfQuestions, selectedTopicsId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
