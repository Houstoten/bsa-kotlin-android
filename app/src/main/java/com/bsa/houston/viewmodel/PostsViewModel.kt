package com.bsa.houston.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.PostRepository

class PostsViewModel(postRepository: PostRepository) : ViewModel() {
    val posts: LiveData<List<Post>> = postRepository.fetchAndGetPosts()
}
