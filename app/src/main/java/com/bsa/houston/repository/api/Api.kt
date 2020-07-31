package com.bsa.houston.repository.api

import com.bsa.houston.repository.data.Post
import com.bsa.houston.repository.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("posts/{id}")
    suspend fun getPosts(@Path("id") id: String = ""): Response<List<Post>>

    @GET("users/{id}")
    suspend fun getUsers(@Path("id") id: String = ""): Response<List<User>>

}