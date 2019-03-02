package ru.falseteam.tasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_task_list.*
import ru.falseteam.tasks.R
import ru.falseteam.tasks.app.App
import ru.falseteam.tasks.database.dao.TaskDao
import ru.falseteam.tasks.database.entity.Task
import ru.falseteam.tasks.databinding.TaskListElementBinding
import javax.inject.Inject

class TaskListFragment : Fragment() {
    @Inject
    lateinit var taskDao: TaskDao

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.dagger.inject(this)

        loadList()
    }

    private fun loadList() {
        val adapter = Adapter(emptyList())
        val elements = taskDao.getAllLiveData()
        elements.observe(this, Observer {
            adapter.element = it
            adapter.notifyDataSetChanged()
        })
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context)
    }

    class Adapter(var element: List<Task>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<TaskListElementBinding>(
                    inflater, R.layout.task_list_element, parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int = element.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(element[position])
        }

        class ViewHolder(private val binding: TaskListElementBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(task: Task) {
                binding.task = task
                binding.executePendingBindings()
            }
        }
    }
}