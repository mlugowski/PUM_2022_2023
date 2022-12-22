package pl.edu.uwr.pum.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ListsAdapter(private val dbHandler: DbHandler) : RecyclerView.Adapter<ListsAdapter.ListsViewHolder>() {
    inner class ListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listName: TextView = itemView.findViewById(R.id.nameList)
        var deleteListButton: Button = itemView.findViewById(R.id.deleteListButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lists_item, parent, false)
        return ListsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        val item = dbHandler.getLists()[position]
        holder.listName.text = item.nameList
        holder.deleteListButton.setOnClickListener {
            dbHandler.deleteList(item.nameList)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }
        holder.listName.setOnClickListener {
            val action = ListsFragmentDirections.actionListsFragmentToDetailFragment(nameList = item.nameList)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dbHandler.getLists().size
    }
}