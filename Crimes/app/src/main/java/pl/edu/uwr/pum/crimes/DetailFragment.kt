package pl.edu.uwr.pum.crimes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    private var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { index = it.getInt("index") }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = Crimes.crimes[index]
        view.findViewById<TextView>(R.id.detailTitle).text = "Title: " + item.title
        view.findViewById<TextView>(R.id.detailContent).text = "Content: " + item.content
        view.findViewById<TextView>(R.id.detailStudentIndex).text = "Student index: " + item.studentIndex
        view.findViewById<TextView>(R.id.detailDate).text = "Date: " + item.date



    }
}