package com.example.jetpackexampleapp.data.model

import androidx.room.*
import java.sql.Time
import java.util.*

@Entity(indices = [Index(value = ["id"], unique = true)])
data class Post (
    var content: String = "",
    var userID: Int,
    var date: String,
    var likes: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)