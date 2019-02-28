package ru.falseteam.tasks.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var title: String = "",
        var notes: String = "",
        var isComplete: Boolean = false
)