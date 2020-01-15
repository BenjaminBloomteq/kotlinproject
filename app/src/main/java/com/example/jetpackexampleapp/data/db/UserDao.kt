package com.example.jetpackexampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackexampleapp.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun findUserById(id: Int) : LiveData<User>
}