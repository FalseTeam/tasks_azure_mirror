package ru.falseteam.tasks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.falseteam.tasks.R
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.dao.TaskDao
import javax.inject.Inject

class DebugActivity : AppCompatActivity() {

    @Suppress("unused") //TODO добавьте кнопки ААА!!!
    @Inject
    lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.dagger.inject(this)
        setContentView(R.layout.activity_debug)
    }
}