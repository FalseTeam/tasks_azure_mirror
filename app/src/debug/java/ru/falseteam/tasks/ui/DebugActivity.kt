package ru.falseteam.tasks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.debug.activity_debug.*
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.entity.Task
import ru.falseteam.tasks.database.repository.TaskRepository
import javax.inject.Inject
import androidx.appcompat.app.AlertDialog
import ru.falseteam.tasks.R

class DebugActivity : AppCompatActivity() {

    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)
        App.dagger.inject(this)

        btn_delete_all.setOnClickListener {
            val confirmingDialog = getConfirmingDialog()
            confirmingDialog.show()
        }

        btn_add_10_items.setOnClickListener {
            val list = (0 until 10).map { Task(title = "task$it") }
            taskRepository.insertAsync(list)
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                        showToast("10 tasks were successfully added")
                    }
        }
    }

    private fun getConfirmingDialog(): AlertDialog.Builder {
        val confirmingDialog = AlertDialog.Builder(this)
        confirmingDialog.setTitle("Are you sure you want to erase all data?")
                .setMessage("Make a choice, Neo!")
                .setPositiveButton("Delete")
                { dialog, _ ->
                    taskRepository.deleteAllAsync()
                            .observeOn(AndroidSchedulers.mainThread()).subscribe { count ->
                                showToast("$count tasks were successfully deleted")
                            }
                    dialog.cancel()
                }
                .setNegativeButton("Cancel")
                { dialog, _ -> dialog.cancel() }
                .setCancelable(false)
        return confirmingDialog
    }
}