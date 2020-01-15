package com.example.jetpackexampleapp.ui.register

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexampleapp.data.model.People
import com.example.jetpackexampleapp.data.repositories.PeopleRepository
import com.raywenderlich.android.imet.data.db.PeopleDao
import javax.inject.Inject

class RegisterViewModel @Inject
constructor(peopleDao: PeopleDao) : ViewModel() {

    private val peopleRepository = PeopleRepository(peopleDao)

    fun registerUser(people: People) {
            peopleRepository.registerPeople(people)
        }

    fun getAllPeople(): LiveData<List<People>> {
        return  peopleRepository.getAllPeople()
    }

    fun userExists(username: String): Boolean {

        val people: People? = peopleRepository.findPersonByUsername(username)
        if (people != null) {
            return true
        }
        return false
    }


}