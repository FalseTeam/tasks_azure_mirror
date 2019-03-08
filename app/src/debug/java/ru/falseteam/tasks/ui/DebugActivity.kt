package ru.falseteam.tasks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.debug.activity_debug.*
import ru.falseteam.tasks.R
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.entity.Task
import ru.falseteam.tasks.database.repository.TaskRepository
import javax.inject.Inject

class DebugActivity : AppCompatActivity() {

    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)
        App.dagger.inject(this)

        btn_delete_all.setOnClickListener {
            taskRepository.deleteAllAsync()
                    .observeOn(AndroidSchedulers.mainThread()).subscribe { count ->
                        showToast("deleted $count items")
                    }
        }

        btn_add_10_items.setOnClickListener {
            val list = (0 until 10).map { Task(title = "task$it") }
            taskRepository.insertAsync(list)
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                        showToast("items added")
                    }
        }
    }
}