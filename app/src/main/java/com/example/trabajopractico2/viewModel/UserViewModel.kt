package com.example.trabajopractico2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.trabajopractico2.data.UserDB
import com.example.trabajopractico2.model.User

class UserViewModel : ViewModel() {

    private val userDao = UserDB.getInstanceDatabase().userDao()

    val readAllData: LiveData<List<User>> = userDao.readAllData()


    fun insertUser(user: User) {
        userDao.insert(user = user)
    }


    fun updateUser(user: User) {
        userDao.update(user = user)
    }


    fun deleteUser(user: User) {
        userDao.deleteUser(user = user)
    }


    fun deleteAllUsers() {
        userDao.deleteAll()
    }

}