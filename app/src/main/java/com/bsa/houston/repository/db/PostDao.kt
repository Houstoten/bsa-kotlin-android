package com.bsa.houston.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsa.houston.repository.data.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM posts ORDER BY id")
    fun getPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPost(postId: Long): LiveData<Post>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>): Void
}