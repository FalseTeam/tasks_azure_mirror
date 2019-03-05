package ru.falseteam.tasks.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task
import java.util.concurrent.Executors
import javax.inject.Inject

class TaskListViewModel : ViewModel() {
    @Inject
    lateinit var taskDao: TaskDao

    val taskList: LiveData<PagedList<Task>>

    init {
        App.dagger.inject(this)

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build()

        taskList = taskDao.getAllDataSource().toLiveData(
                config = config,
                fetchExecutor = Executors.newSingleThreadExecutor()
        )
    }
}