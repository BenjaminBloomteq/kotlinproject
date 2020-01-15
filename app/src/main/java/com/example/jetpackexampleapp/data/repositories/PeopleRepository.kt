package com.example.jetpackexampleapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.jetpackexampleapp.data.model.People
import com.raywenderlich.android.imet.data.db.PeopleDao
import javax.inject.Singleton

@Singleton
class PeopleRepository (
    private val peopleDao: PeopleDao
){

    fun getAllPeople(): LiveData<List<People>> {
        return peopleDao.getAll()
    }

    fun registerPeople(people: People) {
        peopleDao.register(people)
    }

    fun login(username: String, password: String) : People {
        return peopleDao.login(username, password)
    }

    fun setLoggedIn(people: People) {
        peopleDao.setLoggedIn(people.id)
    }

    fun findPeopleById(id: Int): LiveData<People> {
        return peopleDao.find(id)
    }

    fun findPeople(name: String): LiveData<List<People>> {
        return peopleDao.findBy(name)
    }

    fun findPersonByUsername(username: String): People {
        return peopleDao.findByUsername(username)
    }

    //fun register(email: String, password: String, fullName: String) = firebaseSource.register(email, password, fullName)

    //fun login(email: String, password: String) = firebaseSource.login(email, password)



}

