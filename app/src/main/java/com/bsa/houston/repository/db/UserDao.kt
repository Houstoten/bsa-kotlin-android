package com.bsa.houston.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.data.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Long): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>): Void

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(user: User): Void
}