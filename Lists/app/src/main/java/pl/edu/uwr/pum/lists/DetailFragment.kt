package pl.edu.uwr.pum.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class DetailFragment : Fragment() {
    private lateinit var dbHandler: DbHandler
    private lateinit var nameList: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHandler = DbHandler(requireActivity())
        arguments?.let {
            nameList = it.getString("nameList").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasksRV: RecyclerView = view.findViewById(R.id.tasksRV)
        tasksRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TasksAdapter(dbHandler, nameList)
        }
        val addTaskButton: Button = view.findViewById(R.id.addTaskButton)
        val addTaskInput: TextInputEditText = view.findViewById(R.id.addTaskInput)

        addTaskButton.setOnClickListener {
            val task: String = addTaskInput.text.toString()
            if (task.isNotEmpty()) {
                dbHandler.addTask(Task(task, nameList))
                addTaskInput.text?.clear()
            }
            tasksRV.adapter?.notifyItemInserted(dbHandler.getTasks(nameList).size)
        }
    }

    override fun onDestroy() {
        dbHandler.close()
        super.onDestroy()
    }
}