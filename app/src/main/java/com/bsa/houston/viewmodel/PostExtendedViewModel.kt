package com.bsa.houston.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bsa.houston.repository.CommentRepository
import com.bsa.houston.repository.PostRepository
import com.bsa.houston.repository.UserRepository
import com.bsa.houston.repository.data.CollectedById

class PostExtendedViewModel internal constructor(
    postRepository: PostRepository,
    userRepository: UserRepository,
    commentRepository: CommentRepository,
    userId: Long,
    postId: Long
) :
    ViewModel() {
    val collectedById: MediatorLiveData<CollectedById> = MediatorLiveData<CollectedById>().apply {
        value = CollectedById(null, null, null)
    }

    init {
        collectedById.addSource(postRepository.fetchAndGetPost(postId), Observer {
            collectedById.value = CollectedById(collectedById.value?.user, it, collectedById.value?.comments)
        })

        collectedById.addSource(userRepository.fetchAndGetUser(userId), Observer {
            collectedById.value = CollectedById(it, collectedById.value?.post, collectedById.value?.comments)
        })

        collectedById.addSource(commentRepository.fetchAndGetComments(postId), Observer {
            collectedById.value = CollectedById(collectedById.value?.user, collectedById.value?.post, it)
        })

    }


}
