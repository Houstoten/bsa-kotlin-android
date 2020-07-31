package com.bsa.houston.repository.data

data class CollectedById(
    var user: User?,
    var post: Post?,
    var comments: List<Comment>?
)