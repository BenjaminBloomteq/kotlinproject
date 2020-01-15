package com.example.jetpackexampleapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexampleapp.data.repositories.PeopleRepository
import com.example.jetpackexampleapp.data.model.People
import com.raywenderlich.android.imet.data.db.PeopleDao
import javax.inject.Inject

class PeoplesListViewModel @Inject
constructor(
    peopleDao: PeopleDao) : ViewModel() {

    private val peopleRepository = PeopleRepository(peopleDao)
    private val peopleList = MediatorLiveData<List<People>>()


    init {
        getAllPeople()
    }

    // 1
    fun getPeopleList(): LiveData<List<People>> {
        return peopleList
    }

    // 2
    fun getAllPeople() {
//        peopleList.addSource(peopleRepository.getAllPeople()) { peoples ->
//            peopleList.postValue(peoples)
//        }
    }

    fun searchPeople(name: String) {
        // 2
        peopleList.addSource(peopleRepository.findPeople(name)) { peoples ->
            // 3
            peopleList.postValue(peoples)
        }
    }
}