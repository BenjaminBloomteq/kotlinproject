package com.antcolony.social.data.repositories

import com.antcolony.social.data.db.PostDao
import com.antcolony.social.data.model.Post
import javax.inject.Singleton

@Singleton
class PostRepository(private val postDao: PostDao) {

    val posts = postDao.getAllPosts()

    fun insertPost(post: Post) {
        postDao.insert(post)
    }

    fun updatePost(post: Post) {
        postDao.updatePost(post)
    }
}