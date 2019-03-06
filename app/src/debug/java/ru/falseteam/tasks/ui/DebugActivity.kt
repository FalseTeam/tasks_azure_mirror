package ru.falseteam.tasks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.falseteam.tasks.R
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task
import javax.inject.Inject

class DebugActivity : AppCompatActivity() {

    @Inject
    lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)
        App.dagger.inject(this)

        val delete = findViewById<Button>(R.id.btn_delete_all)
        delete.setOnClickListener{
            taskDao.deleteAllOnIO().observeOn(AndroidSchedulers.mainThread()).subscribe{
                count -> Toast.makeText(this,"deleted $count items",Toast.LENGTH_SHORT).show()
            }
        }

        val add = findViewById<Button>(R.id.btn_add_10_items)
        add.setOnClickListener{
            val arrayList = ArrayList<Task>()

            (0 until 10).forEach {
                arrayList.add( Task(title = "task$it"))
            }
            taskDao.insertOnIO(arrayList).observeOn(AndroidSchedulers.mainThread()).subscribe{
                count -> Toast.makeText(this,"added $count items",Toast.LENGTH_SHORT).show()
            }
        }
    }
}