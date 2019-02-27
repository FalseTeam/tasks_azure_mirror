package ru.falseteam.tasks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.falseteam.tasks.database.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * from tasks")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)
}