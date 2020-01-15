package com.example.jetpackexampleapp.ui.feed

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexampleapp.data.db.PostDao
import com.example.jetpackexampleapp.data.model.Post
import com.example.jetpackexampleapp.data.repositories.PostRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(postDao: PostDao) : ViewModel() {

    private var postRepository = PostRepository(postDao)

    fun post(post: Post) {
        postRepository.insertPost(post)
        loadPosts()
    }


    private lateinit var allPosts: MutableLiveData<List<Post>>

    fun getAllPosts(): LiveData<List<Post>> {
        allPosts = MutableLiveData()
        loadPosts()
        return allPosts
    }

    private fun loadPosts() {
            val postsList: List<Post> = postRepository.getAllPosts()
            allPosts.value = postsList
    }


}