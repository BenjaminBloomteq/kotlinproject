package com.example.jetpackexampleapp.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexampleapp.data.db.PostDao
import com.example.jetpackexampleapp.data.model.Post
import com.example.jetpackexampleapp.data.repositories.PostRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(postDao: PostDao) : ViewModel() {

    private val postRepository: PostRepository
    val posts : LiveData<List<Post>>


    init {
        postRepository = PostRepository(postDao)
        posts = postRepository.posts
    }

    fun post(post: Post) {
        postRepository.insertPost(post)
    }

    fun toggleLiked(post: Post, liked: Boolean) {
        post.likes = if (liked) ++post.likes else --post.likes
        postRepository.updatePost(post)
    }



}