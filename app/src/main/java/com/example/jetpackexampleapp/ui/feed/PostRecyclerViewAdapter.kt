package com.example.jetpackexampleapp.ui.feed


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackexampleapp.R
import com.example.jetpackexampleapp.data.model.Post
import kotlinx.android.synthetic.main.fragment_post.view.*
import java.text.SimpleDateFormat
import java.util.*


class PostRecyclerViewAdapter constructor(
    context: Context,
    val onClick: (Post, Boolean) -> Unit
) :
    RecyclerView.Adapter<PostRecyclerViewAdapter.PostViewHolder>() {

    private var posts = emptyList<Post>()
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView = inflater.inflate(R.layout.fragment_post, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        //set values
        holder.bindPost(posts[position])
        holder.itemView.notLikedImageView.setOnClickListener {
            if (holder.itemView.notLikedImageView.tag == "liked") {
                holder.itemView.notLikedImageView.tag = "unliked"
                holder.itemView.notLikedImageView.setImageResource(R.mipmap.unliked)
                onClick(posts[position], false)
            } else {
                holder.itemView.notLikedImageView.tag = "liked"
                holder.itemView.notLikedImageView.setImageResource(R.mipmap.liked)
                onClick(posts[position], true)
            }
        }
    }

    fun setPosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        fun bindPost(post: Post) {
            with(post) {
                itemView.postContentTextView.text = content
                val dateFormatter = SimpleDateFormat("dd/MM HH:mm")
                val fullDate = Date(date)
                val time: String? = dateFormatter.format(fullDate)
                itemView.timePostedTextView.text = time
                itemView.userAvatar.setImageResource(R.drawable.avatar)
                itemView.numberOfLikes.text = post.likes.toString()
            }
        }
    }
}