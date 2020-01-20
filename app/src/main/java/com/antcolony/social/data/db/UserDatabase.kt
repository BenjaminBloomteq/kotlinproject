package com.antcolony.social.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antcolony.social.data.model.User
import com.antcolony.social.data.model.Post

@Database(entities = [User::class, Post::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}