package com.example.jetpackexampleapp.data.repositories

import com.example.jetpackexampleapp.data.db.PostDao
import com.example.jetpackexampleapp.data.model.Post
import javax.inject.Singleton

@Singleton
class PostRepository(private val postDao: PostDao) {

    val posts = postDao.getAllPosts()
    fun insertPost(post: Post) {
        postDao.insert(post)
    }

    fun getAllPosts() : List<Post> {
        return postDao.getAllPosts()
    }
}