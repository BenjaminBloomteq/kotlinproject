package com.antcolony.social.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class Post(
    var content: String = "",
    var userID: Int,
    var date: String,
    var likes: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)