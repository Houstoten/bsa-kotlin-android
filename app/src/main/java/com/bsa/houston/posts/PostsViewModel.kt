package com.bsa.houston.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsa.houston.data.Post
import com.bsa.houston.data.PostRepository

class PostsViewModel(postRepository: PostRepository) : ViewModel() {
    val posts: LiveData<List<Post>> = postRepository.fetchAndGetPosts()
}
