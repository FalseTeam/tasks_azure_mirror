package ru.falseteam.tasks.realm.repository

import io.realm.Realm
import ru.falseteam.tasks.realm.model.Task
import ru.falseteam.tasks.realm.model.TaskFields
import javax.inject.Inject

class TaskRepository @Inject constructor() {
    fun getAll() = Realm.getDefaultInstance().where(Task::class.java).findAll()!!

    fun saveNew(task: Task) {
        Realm.getDefaultInstance().executeTransaction {
            val max = it.where(Task::class.java).max(TaskFields.ID)
            task.id = if (max == null) 0 else max.toInt() + 1
            it.insertOrUpdate(task)
        }
    }
}