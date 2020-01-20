package com.antcolony.social.data.repositories

import androidx.lifecycle.LiveData
import com.antcolony.social.data.db.UserDao
import com.antcolony.social.data.model.User
import javax.inject.Singleton

@Singleton
class UserRepository(
    private val userDao: UserDao
) {

    fun getAllPeople(): LiveData<List<User>> {
        return userDao.getAll()
    }

    fun registerPeople(user: User) {
        userDao.register(user)
    }

    fun login(username: String, password: String): User {
        return userDao.login(username, password)
    }

    fun setLoggedIn(user: User) {
        userDao.setLoggedIn(user.id)
    }

    fun findPeopleById(id: Int): LiveData<User> {
        return userDao.find(id)
    }

    fun findPeople(name: String): LiveData<List<User>> {
        return userDao.findBy(name)
    }

    fun findPersonByUsername(username: String): User {
        return userDao.findByUsername(username)
    }
}

