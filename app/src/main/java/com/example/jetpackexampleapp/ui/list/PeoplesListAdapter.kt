package com.example.jetpackexampleapp.ui.list

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackexampleapp.R
import com.example.jetpackexampleapp.data.model.People
import kotlinx.android.synthetic.main.layout_list_item.view.*

class PeoplesListAdapter(
    private val items: List<People>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position], clickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(people: People, itemView: View)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(people: People, listener: OnItemClickListener) = with(itemView) {
            textViewName.text = people.name
            textViewMet.text = people.metAt
            buttonContact.text = people.contact
            buttonContact.setOnClickListener {
                // Dial contact number
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:${people.contact}")
                itemView.context.startActivity(dialIntent)
            }

            // RecyclerView on item click
            setOnClickListener {
                listener.onItemClick(people, it)
            }
        }
    }
}