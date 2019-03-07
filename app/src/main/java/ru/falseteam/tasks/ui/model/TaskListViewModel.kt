package ru.falseteam.tasks.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.entity.Task
import ru.falseteam.tasks.database.repository.TaskRepository
import java.util.concurrent.Executors
import javax.inject.Inject

class TaskListViewModel : ViewModel() {
    @Inject
    lateinit var taskRepository: TaskRepository

    val taskList: LiveData<PagedList<Task>>

    init {
        App.dagger.inject(this)

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build()

        taskList = taskRepository.getAllDataSource().toLiveData(
                config = config,
                fetchExecutor = Executors.newSingleThreadExecutor()
        )
    }
}