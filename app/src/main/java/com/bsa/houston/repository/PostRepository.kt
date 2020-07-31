package com.bsa.houston.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsa.houston.repository.api.Api
import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.data.User
import com.bsa.houston.repository.db.PostDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

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
            if (id.isEmpty()) {
                val response = api.getPosts().body()
                posts.postValue(response)
            } else {
                val response = api.getPost(id).body()
                posts.postValue(listOf(response) as List<Post>?)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun fetchAndGetPosts(): LiveData<List<Post>> {
        CoroutineScope(Dispatchers.IO).launch {
            fetchPosts()
            postDao.insertAll(posts.value.orEmpty())
        }
        return getPosts()
    }

    fun fetchAndGetPost(id: Long): LiveData<Post> {
        CoroutineScope(Dispatchers.IO).launch {
            fetchPosts(id.toString())
            posts.value?.get(0)?.let { postDao.insertOne(it) }
        }
        return getPost(id)
    }

    private fun savePosts(posts: List<Post>) = CoroutineScope(Dispatchers.IO).launch { postDao.insertAll(posts) }


}