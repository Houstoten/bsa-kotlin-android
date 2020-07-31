package com.bsa.houston.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsa.houston.repository.api.Api
import com.bsa.houston.repository.data.Comment
import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.db.CommentDao
import com.bsa.houston.repository.db.PostDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentRepository(private val commentDao: CommentDao, private val api: Api) {

    private val comments = MutableLiveData<List<Comment>>()

    init {
        comments.observeForever {
            saveComments(it)
        }
    }

    private fun getComments() = commentDao.getComments()

    fun getCommentByPost(postId: Long) = commentDao.getCommentsByPostId(postId)

    fun getComment(id: Long) = commentDao.getComment(id)

    private suspend fun fetchComments(id: String) {
        try {
            val response = api.getPostComments(id).body()
            comments.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun fetchAndGetComments(id: Long): LiveData<List<Comment>> {
        CoroutineScope(Dispatchers.IO).launch {
            fetchComments(id.toString())
            comments.value?.let { commentDao.insertAll(it) }
        }
        return getCommentByPost(id)
    }

    private fun saveComments(comments: List<Comment>) =
        CoroutineScope(Dispatchers.IO).launch { commentDao.insertAll(comments) }


}