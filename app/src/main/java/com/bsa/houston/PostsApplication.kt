package com.bsa.houston

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bsa.houston.api.Api
import com.bsa.houston.data.AppDatabase
import com.bsa.houston.data.PostRepository
import com.bsa.houston.posts.PostsViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsApplication : Application() {
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var api: Api
        private lateinit var postsRepository: PostRepository
        private lateinit var postsViewModel: PostsViewModel
        private lateinit var appDatabase: AppDatabase

        fun injectApi() = api

        fun injectViewModel() = postsViewModel

        fun injectPostDao() = appDatabase.postDao()

    }

    override fun onCreate() {

        retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        api = retrofit.create(Api::class.java)
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mvvm-database"
        ).build()

        postsRepository = PostRepository(appDatabase.postDao(), api)
        postsViewModel = PostsViewModel(postsRepository)
        super.onCreate()

    }
}