package com.bsa.houston.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bsa.houston.repository.PostRepository

class PostExtendedViewModelFactory(private val postRepository: PostRepository, private val userId: Long) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostExtendedViewModel(postRepository, userId) as T
    }
}