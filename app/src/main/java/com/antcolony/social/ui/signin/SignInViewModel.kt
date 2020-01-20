package com.antcolony.social.ui.signin

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.antcolony.social.data.db.UserDao
import com.antcolony.social.data.model.User
import com.antcolony.social.data.repositories.UserRepository
import javax.inject.Inject


class SignInViewModel @Inject constructor(userDao: UserDao) : ViewModel() {

    private var userRepository = UserRepository(userDao)

    @Inject
    lateinit var application: Application

    fun loginUser(username: String) {
        val people: User = userRepository.findPersonByUsername(username)
        userRepository.setLoggedIn(people)
    }

    fun validateUserExists(username: String, password: String): Boolean {
        val existingPerson: User? = userRepository.login(username, password)
        if (existingPerson == null) {
            Toast.makeText(application, "Username or password is incorrect", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}