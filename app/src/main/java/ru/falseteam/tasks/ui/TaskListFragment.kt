package ru.falseteam.tasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_task_list.*
import ru.falseteam.tasks.R
import ru.falseteam.tasks.database.entity.Task
import ru.falseteam.tasks.databinding.TaskListElementBinding
import ru.falseteam.tasks.ui.model.TaskListViewModel

class TaskListFragment : Fragment() {
    private lateinit var viewModel: TaskListViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)

        val adapter = Adapter()
        list.adapter = adapter
        viewModel.taskList.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    class Adapter : PagedListAdapter<Task, Adapter.ViewHolder>(diffCallback) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<TaskListElementBinding>(
                    inflater, R.layout.task_list_element, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        class ViewHolder(private val binding: TaskListElementBinding) :
                RecyclerView.ViewHolder(binding.root) {
            fun bind(task: Task?) {
                binding.task = task
                binding.executePendingBindings()
            }
        }

        companion object {
            private val diffCallback = object : DiffUtil.ItemCallback<Task>() {
                override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                        oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                        oldItem == newItem
            }
        }
    }
}