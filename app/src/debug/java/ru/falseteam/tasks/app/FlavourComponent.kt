package ru.falseteam.tasks.app

import ru.falseteam.tasks.ui.DebugActivity

interface FlavourComponent {
    fun inject(debugActivity: DebugActivity)
}