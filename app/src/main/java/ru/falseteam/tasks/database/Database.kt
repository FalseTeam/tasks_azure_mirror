package ru.falseteam.tasks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.falseteam.tasks.database.converter.DateConverter
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}