package com.example.jetpackexampleapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.jetpackexampleapp.data.db.PeopleDatabase
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

    fun insertPeople(people: People) {
        peopleDao.insert(people)
    }

    fun findPeople(id: Int): LiveData<People> {
        return peopleDao.find(id)
    }

    fun findPeople(name: String): LiveData<List<People>> {
        return peopleDao.findBy(name)
    }


}

