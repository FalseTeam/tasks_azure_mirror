package ru.falseteam.tasks.database.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task

class TaskRepository(taskDao: TaskDao) : TaskDao by taskDao {
    fun insertOnIO(task: Task) =
            Single.fromCallable { insert(task) }.subscribeOn(Schedulers.io())

    fun insertOnIO(task: List<Task>): Single<Unit> =
            Single.fromCallable { insert(task) }.subscribeOn(Schedulers.io())

    fun deleteAllOnIO(): Single<Int> =
            Single.fromCallable { deleteAll() }.subscribeOn(Schedulers.io())
}