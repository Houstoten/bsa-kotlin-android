package com.bsa.houston.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsa.houston.repository.api.Api
import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.db.PostDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostRepository(private val postDao: PostDao, private val api: Api) {

    private val posts = MutableLiveData<List<Post>>()

    init {
        posts.observeForever {
            savePosts(it)
        }
    }

    private fun getPosts() = postDao.getPosts()

    fun getPost(postId: Long) = postDao.getPost(postId)

    private suspend fun fetchPosts(id: String = "") {
        try {
            val response = api.getPosts(id).body()
            posts.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun fetchAndGetPosts(id: String = ""): LiveData<List<Post>> {
        CoroutineScope(Dispatchers.IO).launch {
            fetchPosts(id)
            postDao.insertAll(posts.value.orEmpty())
        }
        return getPosts()
    }

    private fun savePosts(posts: List<Post>) = CoroutineScope(Dispatchers.IO).launch { postDao.insertAll(posts) }


}