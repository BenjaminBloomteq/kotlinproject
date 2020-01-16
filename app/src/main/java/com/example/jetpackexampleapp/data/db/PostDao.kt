package com.example.jetpackexampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpackexampleapp.data.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Query("SELECT * FROM Post ORDER BY id DESC")
    fun getAllPosts() : LiveData<List<Post>>

    @Update
    fun updatePost(post: Post)
}