package ru.falseteam.tasks.ui

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import io.realm.Realm
import ru.falseteam.tasks.App
import ru.falseteam.tasks.R
import ru.falseteam.tasks.realm.model.Task
import ru.falseteam.tasks.realm.model.TaskFields
import ru.falseteam.tasks.realm.repository.TaskRepository
import javax.inject.Inject


class AddTaskPopup(context: Context) {
    private val dialog = Dialog(context)

    @Inject
    lateinit var taskRepository: TaskRepository

    init {
        App.dagger.inject(this)
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
            taskRepository.saveNew(Task(title = title.text.toString(), notes = notes.text.toString()))
            dialog.dismiss()
        }

        close.setOnClickListener { dialog.dismiss() }

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