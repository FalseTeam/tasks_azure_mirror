package ru.falseteam.tasks.app

import dagger.Component
import ru.falseteam.tasks.database.DatabaseModule
import ru.falseteam.tasks.ui.AddTaskPopup
import ru.falseteam.tasks.ui.MainActivity
import javax.inject.Singleton

@Component(modules = [AppModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(addTaskPopup: AddTaskPopup)
}