package ru.falseteam.tasks.ui

import dagger.Component

@Component
interface UiComponent {
    fun inject(mainActivity: MainActivity)
}