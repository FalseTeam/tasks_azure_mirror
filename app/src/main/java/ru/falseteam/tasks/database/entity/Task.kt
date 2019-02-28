package ru.falseteam.tasks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var title: String = "",
        var notes: String = "",
        @ColumnInfo(name = "is_complete") var isComplete: Boolean = false,
        var priority: Int = 1,
        @ColumnInfo(name = "create_timestamp") var createTimestamp: Long = 0,
        @ColumnInfo(name = "last_edit_timestamp") var lastEditTimestamp: Long = 0
)