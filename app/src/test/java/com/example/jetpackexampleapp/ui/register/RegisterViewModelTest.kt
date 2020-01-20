package com.example.jetpackexampleapp.ui.register

import com.antcolony.social.ui.register.RegisterViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.antcolony.social.data.db.UserDao
import com.antcolony.social.data.model.User
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class RegisterViewModelTest {

    private lateinit var registerViewModel: RegisterViewModel
    private var userDao: UserDao = mock()

    @Before
    fun before() {
        registerViewModel = RegisterViewModel(userDao)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun registerUserWhenUserObjectIsValid() {
        val user = User(
            "TestUserNew",
            "testuserNew@mail.com",
            "test123",
            "testuserNew@mail.com",
            0,
            0
        )

        registerViewModel.registerUser(user)
        verify(userDao, Mockito.times(1)).register(user)
    }

    @Test
    fun registerUserWhenUserObjectIsInvalid() {
        val user = User(
            "TestUserNew",
            "testuserinvalidEmail",
            "test123",
            "testuserInvalidEmail",
            0,
            0
        )
        registerViewModel.registerUser(user)
        verify(userDao, Mockito.never()).register(user)
    }
}