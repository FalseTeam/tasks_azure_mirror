package ru.falseteam.tasks.database.repository

import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task
import java.util.*

class TaskRepository(taskDao: TaskDao) : AbstractRepository(), TaskDao by taskDao {
    fun insertAsync(task: Task) = asyncSingle { insert(task) }

    fun insertAsync(task: List<Task>) = asyncCompletable { insert(task) }

    fun updateCompleteAsync(id: Long, complete: Boolean, date: Date = Date()) =
            asyncSingle { updateComplete(id, complete, date) }

    fun updateAsync(task: Task) = asyncSingle { update(task) }

    fun deleteAllAsync() = asyncSingle { deleteAll() }
}