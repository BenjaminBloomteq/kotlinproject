package com.antcolony.social.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id", "email", "username"], unique = true)])
data class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var username: String = "",
    var loggedIn: Int = 0,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
