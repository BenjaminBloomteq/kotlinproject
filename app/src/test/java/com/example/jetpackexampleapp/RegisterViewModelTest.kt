package com.example.jetpackexampleapp

import androidx.lifecycle.LiveData
import com.example.jetpackexampleapp.data.model.People
import com.example.jetpackexampleapp.data.repositories.PeopleRepository
import com.example.jetpackexampleapp.ui.register.RegisterViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raywenderlich.android.imet.data.db.PeopleDao
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {

    private lateinit var registerViewModel: RegisterViewModel

    private val peopleDao : PeopleDao = mock()
    private val peopleRepository : PeopleRepository = mock()

    @Before
    fun before() {
        registerViewModel = RegisterViewModel(peopleDao)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchUserShouldReturnUserWhenUserObjectIsValid() {
        `when`<Any>(peopleRepository.findPeopleById(0)).thenReturn()
        verify(peopleRepository, Mockito.times(1)).findPeopleById(0)
    }
}