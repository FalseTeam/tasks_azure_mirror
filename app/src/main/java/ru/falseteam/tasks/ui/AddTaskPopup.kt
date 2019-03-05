package ru.falseteam.tasks.ui

import android.app.Dialog
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.falseteam.tasks.R
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task
import javax.inject.Inject


class AddTaskPopup(activity: ComponentActivity) {
    private val dialog = Dialog(activity)

    @Inject
    lateinit var taskDao: TaskDao

    init {
        App.dagger.inject(this)
        dialog.setContentView(R.layout.add_task_popup)
        setupLayoutParams()

        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        val title = dialog.findViewById<EditText>(R.id.title)
        val notes = dialog.findViewById<EditText>(R.id.notes)
        val save = dialog.findViewById<ImageView>(R.id.btn_save)
        val swap = dialog.findViewById<ImageView>(R.id.btn_swap)
        val close = dialog.findViewById<ImageView>(R.id.btn_close)
        save.isEnabled = false

        title.addTextChangedListener {
            save.isEnabled = it.isNotEmpty()
        }

        save.setOnClickListener {
            save.isEnabled = false //TODO fix this
            val insertOnIO = taskDao.insertOnIO(Task(title = title.text.toString(), notes = notes.text.toString()))
            insertOnIO
                    .observeOn(AndroidSchedulers.mainThread())
//                    .bindToLifecycle(AndroidLifecycle.createLifecycleProvider(activity))//TODO check
                    .subscribe { _ -> dialog.dismiss() }
        }

        swap.setOnClickListener {
            val params = save.layoutParams
            save.layoutParams = close.layoutParams
            close.layoutParams = params
        }

        close.setOnClickListener { dialog.dismiss() }
    }

    fun show() {
        dialog.show()
    }

    private fun setupLayoutParams() {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = layoutParams
    }
}