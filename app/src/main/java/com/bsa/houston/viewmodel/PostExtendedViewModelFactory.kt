package com.bsa.houston.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bsa.houston.repository.CommentRepository
import com.bsa.houston.repository.PostRepository
import com.bsa.houston.repository.UserRepository

class PostExtendedViewModelFactory(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
    private val userId: Long,
    private val postId: Long
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostExtendedViewModel(postRepository, userRepository, commentRepository, userId, postId) as T
    }
}