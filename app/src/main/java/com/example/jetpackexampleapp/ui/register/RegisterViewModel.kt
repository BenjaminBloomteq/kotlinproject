package com.example.jetpackexampleapp.ui.register

import android.util.Patterns
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexampleapp.data.model.People
import com.example.jetpackexampleapp.data.repositories.PeopleRepository
import com.raywenderlich.android.imet.data.db.PeopleDao
import javax.inject.Inject

class RegisterViewModel @Inject
constructor(peopleDao: PeopleDao) : ViewModel() {

    private val peopleRepository = PeopleRepository(peopleDao)

    fun getAllPeople(): LiveData<List<People>> {
        return  peopleRepository.getAllPeople()
    }

    private fun validateEmail(email: String) : Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun userExists(username: String): Boolean {

        val people: People? = peopleRepository.findPersonByUsername(username)
        if (people != null) {
            return true
        }
        return false
    }

    fun registerUser(people: People) {
        if (validateEmail(people.username)) {
            peopleRepository.registerPeople(people)
        }
    }

}