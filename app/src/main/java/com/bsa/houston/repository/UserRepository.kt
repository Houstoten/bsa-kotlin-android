package com.bsa.houston.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsa.houston.repository.api.Api
import com.bsa.houston.repository.data.User
import com.bsa.houston.repository.db.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao, private val api: Api) {

    private val users = MutableLiveData<List<User>>()

    init {
        users.observeForever {
            saveUsers(it)
        }
    }

    private fun getUsers() = userDao.getUsers()

    fun getUser(userId: Long) = userDao.getUser(userId)

    private suspend fun fetchUsers(id: String = "") {
        try {
            val response = api.getUsers(id).body()
            users.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun fetchAndGetUsers(id: String = ""): LiveData<List<User>> {
        CoroutineScope(Dispatchers.IO).launch {
            fetchUsers(id)
            userDao.insertAll(users.value.orEmpty())
        }
        return getUsers()
    }

    private fun saveUsers(users: List<User>) = CoroutineScope(Dispatchers.IO).launch { userDao.insertAll(users) }


}