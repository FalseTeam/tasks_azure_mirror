package ru.falseteam.tasks.database.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task

class TaskRepository(taskDao: TaskDao) : AbstractRepository(), TaskDao by taskDao {
    fun insertOnIO(task: Task) = asyncSingle { insert(task) }

    fun insertOnIO(task: List<Task>) = asyncCompletable { insert(task) }

    fun deleteAllOnIO() = asyncSingle { deleteAll() }
}