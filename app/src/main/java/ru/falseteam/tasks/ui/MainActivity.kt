package ru.falseteam.tasks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.R
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.dagger.inject(this)
        setContentView(R.layout.activity_main)
        button_add.setOnClickListener { AddTaskPopup(this).show() }

        loadList()
    }

    private fun loadList() {
        val elements = taskDao.getAll()
        recycler_view.adapter = Adapter(elements)
        recycler_view.layoutManager = LinearLayoutManager(this)
//        elements.addChangeListener { _ -> (recycler_view.adapter as Adapter).notifyDataSetChanged() }
    }


    class Adapter(private val element: List<Task>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_element, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = element.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = element[position].title
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.text_title)
        }
    }
}
