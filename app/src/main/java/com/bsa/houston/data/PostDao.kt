package com.bsa.houston.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Query("SELECT * FROM posts ORDER BY id")
    fun getPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPost(postId: Int): LiveData<Post>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Post>): Void
}