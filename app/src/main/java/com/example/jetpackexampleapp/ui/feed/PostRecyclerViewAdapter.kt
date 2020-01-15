package com.example.jetpackexampleapp.ui.feed


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackexampleapp.R
import com.example.jetpackexampleapp.data.model.Post
import java.text.SimpleDateFormat
import java.util.*


class PostRecyclerViewAdapter constructor(
    private val livedata: LiveData<List<Post>>
) :
    RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get view reference
        var content: TextView = view.findViewById(R.id.postContentTextView) as TextView
        var date: TextView = view.findViewById(R.id.timePostedTextView) as TextView
        var likes: TextView = view.findViewById(R.id.numberOfLikes) as TextView
        var liked: ImageView = view.findViewById(R.id.notLikedImageView) as ImageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create view holder to hold reference
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_post,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //set values
        val dateFormatter = SimpleDateFormat("dd/MM HH:mm")
        val data = livedata.value!! // idk
        holder.content.text = data[position].content
        val date = Date(data[position].date)
        val time: String? = dateFormatter.format(date)
        holder.date.text = time
        holder.likes.text = String.format("%d", 100)
        holder.liked.setImageResource(R.mipmap.unliked)
        holder.liked.tag = "unliked"

        holder.liked.setOnClickListener {
            if (holder.liked.tag == "liked") {
                val likes = Integer.parseInt(holder.likes.text.toString()) - 1
                holder.liked.setImageResource(R.mipmap.unliked)
                holder.liked.tag = "unliked"
                holder.likes.text = likes.toString()
            } else {
                val likes = Integer.parseInt(holder.likes.text.toString()) + 1
                holder.liked.setImageResource(R.mipmap.liked)
                holder.liked.tag = "liked"
                holder.likes.text = likes.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return livedata.value!!.size
    }
}