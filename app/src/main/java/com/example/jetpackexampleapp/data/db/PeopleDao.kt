package com.raywenderlich.android.imet.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackexampleapp.data.model.People

@Dao
interface PeopleDao {

    // 1: Select All
    @Query("SELECT * FROM People ORDER BY id DESC")
    fun getAll(): LiveData<List<People>>

    // 2: Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(people: People)

    // 3: Delete
    @Query("DELETE FROM People")
    fun deleteAll()

    // 4: Select by id
    @Query("SELECT * FROM People WHERE id = :id")
    fun find(id: Int): LiveData<People>

    @Query("SELECT * FROM People WHERE name LIKE '%' || :name || '%'")
    fun findBy(name: String): LiveData<List<People>>

}
