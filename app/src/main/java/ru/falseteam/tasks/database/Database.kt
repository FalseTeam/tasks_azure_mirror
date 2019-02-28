package ru.falseteam.tasks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}