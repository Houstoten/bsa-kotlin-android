package com.bsa.houston.repository.api

import com.bsa.houston.repository.data.Comment
import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: String): Response<Post>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): Response<User>

    @GET("comments")
    suspend fun getPostComments(@Query("postId") id: String): Response<List<Comment>>
}