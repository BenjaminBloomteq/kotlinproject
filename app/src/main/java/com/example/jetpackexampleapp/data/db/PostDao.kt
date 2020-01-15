package com.example.jetpackexampleapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackexampleapp.data.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Query("SELECT * FROM Post ORDER BY id DESC")
    fun getAllPosts() : List<Post>
}