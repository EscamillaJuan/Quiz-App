package com.app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic")
data class Topic(
    @PrimaryKey @ColumnInfo(name = "_id") val id: Int,
    val title: String,
    @ColumnInfo(name = "icon") val icon: Int,
)

