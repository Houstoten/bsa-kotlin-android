package com.bsa.houston.api

import com.bsa.houston.data.Post
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

}