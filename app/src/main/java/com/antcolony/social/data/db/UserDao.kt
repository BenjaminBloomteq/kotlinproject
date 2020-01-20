package com.antcolony.social.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antcolony.social.data.model.User

@Dao
interface UserDao {

    // 1: Select All
    @Query("SELECT * FROM User ORDER BY id DESC")
    fun getAll(): LiveData<List<User>>

    // 2: Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(people: User)

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    fun login(username: String, password: String): User

    @Query("UPDATE User SET loggedIn = 1 WHERE id = :id")
    fun setLoggedIn(id: Int)

    // 3: Delete
    @Query("DELETE FROM User")
    fun deleteAll()

    // 4: Select by id
    @Query("SELECT * FROM User WHERE id = :id")
    fun find(id: Int): LiveData<User>

    @Query("SELECT * FROM User WHERE name LIKE '%' || :name || '%'")
    fun findBy(name: String): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE username = :username")
    fun findByUsername(username: String): User
}


