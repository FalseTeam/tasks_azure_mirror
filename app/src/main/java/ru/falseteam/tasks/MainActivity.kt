package ru.falseteam.tasks

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import ru.falseteam.tasks.realm.model.Task

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_add.setOnClickListener { AddTaskPopup(this).show() }

        loadList()
    }

    private fun loadList() {
        val elements = Realm.getDefaultInstance().where(Task::class.java).findAll()
        recycler_view.adapter = Adapter(elements)
        recycler_view.layoutManager = LinearLayoutManager(this)
        elements.addChangeListener { _ -> recycler_view.adapter.notifyDataSetChanged() }
    }


    class Adapter(val element: RealmResults<Task>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return if (element.isValid) element.size else 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = element[position]!!.title
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text: TextView = itemView.findViewById(android.R.id.text1)
        }
    }
}
