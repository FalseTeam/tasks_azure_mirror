package ru.falseteam.tasks

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import io.realm.Realm
import ru.falseteam.tasks.realm.model.Task
import ru.falseteam.tasks.realm.model.TaskFields


class AddTaskPopup(private val context: Context) {
    private val dialog = Dialog(context)

    init {
        dialog.setContentView(R.layout.add_task_popup)
        setupLayoutParams()

        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        val title = dialog.findViewById<EditText>(R.id.title)
        val notes = dialog.findViewById<EditText>(R.id.notes)
        val save = dialog.findViewById<Button>(R.id.btn_save)
        val close = dialog.findViewById<ImageView>(R.id.btn_close)
        save.isEnabled = false

        title.addTextChangedListener {
            save.isEnabled = it.isNotEmpty()
        }

        save.setOnClickListener {
            saveTaskToDb(Task(title = title.text.toString(), notes = notes.text.toString()))
            dialog.dismiss()
        }

        close.setOnClickListener { dialog.dismiss() }

    }

    private fun saveTaskToDb(task: Task) {
        Realm.getDefaultInstance().executeTransaction {
            val max = it.where(Task::class.java).max(TaskFields.ID)
            task.id = if (max == null) 0 else max.toInt() + 1
            it.insertOrUpdate(task)
        }
    }

    fun show() {
        dialog.show()
//        context.openKeyboard()
    }

    private fun setupLayoutParams() {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window.attributes = layoutParams
    }
}