package com.bsa.houston.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsa.houston.repository.PostRepository
import com.bsa.houston.repository.data.Post

class PostExtendedViewModel internal constructor(postRepository: PostRepository, userId: Long) :
    ViewModel() {
    val post: LiveData<Post> = postRepository.getPost(userId)
}
