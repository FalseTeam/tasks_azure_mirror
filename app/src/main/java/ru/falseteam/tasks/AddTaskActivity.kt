package ru.falseteam.tasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_task.*
import ru.falseteam.tasks.realm.model.Task
import ru.falseteam.tasks.realm.model.TaskFields

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        btn_save.setOnClickListener {
            if (edit_text.text.isEmpty()) return@setOnClickListener
            saveTaskToDb()
            finish()
        }
    }

    private fun saveTaskToDb() {
        val task = Task(title = edit_text.text.toString())
        Realm.getDefaultInstance().executeTransaction {
            val max = it.where(Task::class.java).max(TaskFields.ID)
            task.id = if (max == null) 0 else max.toInt() + 1
            it.insertOrUpdate(task)
        }
    }
}
