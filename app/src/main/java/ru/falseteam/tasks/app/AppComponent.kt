package ru.falseteam.tasks.app

import dagger.Component
import ru.falseteam.tasks.database.DatabaseModule
import ru.falseteam.tasks.ui.AddTaskPopup
import ru.falseteam.tasks.ui.MainActivity
import ru.falseteam.tasks.ui.TaskListFragment
import javax.inject.Singleton

@Component(modules = [AppModule::class, DatabaseModule::class])
@Singleton
interface AppComponent : FlavourComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(addTaskPopup: AddTaskPopup)
    fun inject(taskListFragment: TaskListFragment)
}