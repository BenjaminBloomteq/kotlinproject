package com.example.jetpackexampleapp.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexampleapp.data.db.PostDao
import com.example.jetpackexampleapp.data.model.Post
import com.example.jetpackexampleapp.data.repositories.PeopleRepository
import com.example.jetpackexampleapp.data.repositories.PostRepository
import com.raywenderlich.android.imet.data.db.PeopleDao
import javax.inject.Inject

class PostViewModel @Inject constructor(postDao: PostDao) : ViewModel() {

    private var postRepository = PostRepository(postDao)

    fun post(post: Post) {
        postRepository.insertPost(post)
    }

//    fun getPosts() : LiveData<List<Post>> {
//        return postRepository.getAllPosts()
//    }
}