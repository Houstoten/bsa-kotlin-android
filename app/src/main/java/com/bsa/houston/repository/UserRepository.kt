package com.bsa.houston.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsa.houston.repository.api.Api
import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.data.User
import com.bsa.houston.repository.db.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

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
            if (id.isEmpty()) {
                val response = api.getUsers().body()
                users.postValue(response)
            } else {
                val response = api.getUser(id).body()
                users.postValue(listOf(response) as List<User>?)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun fetchAndGetUsers(): LiveData<List<User>> {
        CoroutineScope(Dispatchers.IO).launch {
            fetchUsers()
            userDao.insertAll(users.value.orEmpty())
        }
        return getUsers()
    }

    fun fetchAndGetUser(id: Long): LiveData<User> {
        CoroutineScope(Dispatchers.IO).launch {
            fetchUsers(id.toString())
            users.value?.get(0)?.let { userDao.insertOne(it) }
        }
        return getUser(id)
    }

    private fun saveUsers(users: List<User>) = CoroutineScope(Dispatchers.IO).launch { userDao.insertAll(users) }


}