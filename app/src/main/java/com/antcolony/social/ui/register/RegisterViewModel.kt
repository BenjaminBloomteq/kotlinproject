package com.antcolony.social.ui.register

import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import com.antcolony.social.data.db.UserDao
import com.antcolony.social.data.model.User
import com.antcolony.social.data.repositories.UserRepository
import javax.inject.Inject

class RegisterViewModel @Inject
constructor(userDao: UserDao) : ViewModel() {

    private val userRepository = UserRepository(userDao)

    fun validateEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun userExists(username: String): Boolean {
        val peopleVal: User? = userRepository.findPersonByUsername(username)
        if (peopleVal != null) {
            return true
        }
        return false
    }

    fun registerUser(user: User) {
        if (!userExists(user.username)) {
            userRepository.registerPeople(user)
        }
    }

}