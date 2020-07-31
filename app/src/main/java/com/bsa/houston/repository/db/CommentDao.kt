package com.bsa.houston.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsa.houston.repository.data.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments ORDER BY id")
    fun getComments(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE id = :id")
    fun getComment(id: Long): LiveData<Comment>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getCommentsByPostId(postId: Long):LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<Comment>): Void

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(comment: Comment): Void
}