package com.example.jetpackexampleapp.ui.signin

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.jetpackexampleapp.data.model.People
import com.example.jetpackexampleapp.data.repositories.PeopleRepository
import com.raywenderlich.android.imet.data.db.PeopleDao
import javax.inject.Inject


class SignInViewModel @Inject constructor(peopleDao: PeopleDao) : ViewModel() {

    private var peopleRepository = PeopleRepository(peopleDao)

    @Inject
    lateinit var application: Application

    fun loginUser(username: String) {
        val people : People = peopleRepository.findPersonByUsername(username)
        peopleRepository.setLoggedIn(people)
    }

    fun validateUserExists(username: String, password: String): Boolean {
        val existingPerson : People? = peopleRepository.login(username, password)
        if (existingPerson == null) {
            Toast.makeText(application, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}