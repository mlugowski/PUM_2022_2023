package pl.edu.uwr.pum.crimes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    private val list = Crimes.crimes

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.itemTitle)
        val date: TextView = view.findViewById(R.id.itemDate)
        val image: ImageView = view.findViewById(R.id.itemImage)
        val itemLayout: ConstraintLayout = view.findViewById(R.id.itemLayout)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.date.text = item.date
        if (item.isSolved) {
            holder.image.visibility = View.VISIBLE
        }
        else {
            holder.image.visibility = View.INVISIBLE
        }

        holder.itemLayout.setOnClickListener {
            val action = ListFragmentDirections
                .actionListFragmentToDetailFragment(
                    index = position
                )
            holder.view.findNavController().navigate(action)
        }
    }
}