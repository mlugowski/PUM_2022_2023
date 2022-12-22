package pl.edu.uwr.pum.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private val dbHandler: DbHandler, private val nameList: String) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    inner class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var taskName: TextView = itemView.findViewById(R.id.nameTask)
        var deleteTaskButton: Button = itemView.findViewById(R.id.deleteTaskButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = dbHandler.getTasks(nameList)[position]
        holder.taskName.text = item.nameTask
        holder.deleteTaskButton.setOnClickListener {
            dbHandler.deleteTask(item.id)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }
    }

    override fun getItemCount(): Int {
        return dbHandler.getTasks(nameList).size
    }
}