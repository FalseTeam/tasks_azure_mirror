package ru.falseteam.tasks

import dagger.Component
import ru.falseteam.tasks.ui.AddTaskPopup
import ru.falseteam.tasks.ui.MainActivity

@Component
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(addTaskPopup: AddTaskPopup)
}