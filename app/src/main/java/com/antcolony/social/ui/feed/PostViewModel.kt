package com.antcolony.social.ui.feed

import androidx.lifecycle.ViewModel
import com.antcolony.social.data.db.PostDao
import com.antcolony.social.data.model.Post
import com.antcolony.social.data.repositories.PostRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(postDao: PostDao) : ViewModel() {

    private var postRepository = PostRepository(postDao)

    fun post(post: Post) {
        postRepository.insertPost(post)
    }
}