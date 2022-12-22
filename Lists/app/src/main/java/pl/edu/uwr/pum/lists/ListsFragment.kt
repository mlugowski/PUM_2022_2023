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

class ListsFragment : Fragment() {
    private lateinit var dbHandler: DbHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHandler = DbHandler(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listsRV: RecyclerView = view.findViewById(R.id.listsRV)
        listsRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListsAdapter(dbHandler)
        }
        val addListButton: Button = view.findViewById(R.id.addListButton)
        val addListInput: TextInputEditText = view.findViewById(R.id.addListInput)

        addListButton.setOnClickListener {
            val nameList: String = addListInput.text.toString()
            if (nameList.isNotEmpty()) {
                dbHandler.addList(nameList)
                addListInput.text?.clear()
            }
            listsRV.adapter?.notifyItemInserted(dbHandler.getLists().size)
        }
    }

    override fun onDestroy() {
        dbHandler.close()
        super.onDestroy()
    }

}