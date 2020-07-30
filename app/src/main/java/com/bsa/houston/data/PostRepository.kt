package com.bsa.houston.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsa.houston.api.Api
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

    fun getPost(postId: Int) = postDao.getPost(postId)

    private suspend fun fetchPosts() {
        try {
            val response = api.getPosts().body()
            posts.postValue(response)
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

    private fun savePosts(posts: List<Post>) = CoroutineScope(Dispatchers.IO).launch { postDao.insertAll(posts) }


}